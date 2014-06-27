package controllers

import play.api._
import play.api.mvc._


import play.api.data._
import play.api.data.Forms._
import play.api.data.Form

import org.squeryl.PrimitiveTypeMode._
import models.{AppDB,Contact}





object Application extends Controller {
  val contactForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "telephone" -> nonEmptyText
    )(Contact.apply)(Contact.unapply)
  )

  def getContacts = {
    inTransaction {
      from(AppDB.contactTable)(contactTable => select(contactTable)).toList
    }
  }

  def index = Action {
    Redirect(routes.Application.contacts)
  }

  def contacts = Action {
    val contacts = getContacts
    Ok(views.html.index(contacts,contactForm))
  }

  def newContact = Action { implicit request => 
    contactForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(getContacts,errors)),
      contact => {
        inTransaction(AppDB.contactTable insert contact)
        Redirect(routes.Application.contacts)        
      }
    )
  }

  def deleteContact(id: Long) = Action {
    inTransaction {
      AppDB.contactTable.delete(id)
    }

    Redirect(routes.Application.contacts)

  }
  
}