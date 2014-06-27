package models
import org.squeryl.{Schema, KeyedEntity}

case class Contact(name: String, telephone: String) extends KeyedEntity[Long] {
    val id: Long = 0
}

object AppDB extends Schema {
    var contactTable = table[Contact]("contact")
}


