package com.example

trait MySqlComponent extends DBComponent{

  override val driver = slick.jdbc.MySQLProfile
  import driver.api._
  override val db: Database = Database.forConfig("myMySqlDB")

}
