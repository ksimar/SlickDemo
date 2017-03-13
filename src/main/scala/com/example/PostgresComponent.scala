package com.example

trait PostgresComponent extends DBComponent{

  override val driver = slick.jdbc.PostgresProfile
  import driver.api._
  override val db: Database = Database.forConfig("myPostgresDB")

}

