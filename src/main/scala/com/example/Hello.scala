package com.example

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Hello extends App {

  val emp = Employee(1, "simar", 20)
  EmployeeComponent.create
  val insertRes = EmployeeComponent.insert(emp)
  val res = insertRes.map{res => s"$res inserted"}
  res.map(println(_))
  //ProjectComponent.create
  DependentComponent.create
  val insert2 = EmployeeComponent.insert(Employee(2, "charmy", 10))
  val insert3 = EmployeeComponent.insert(Employee(3, "aman", 7))
  val insert4 = EmployeeComponent.insert(Employee(4, "ruchi", 2))
  val insert5 = EmployeeComponent.insert(Employee(5, "sonam", 0))
  //val insertProject1 = ProjectComponent.insert(Project(1, "Carbon Data"))
  //val insertProject2 = ProjectComponent.insert(Project(2, "Fitfyles"))
  val insertDependent1 = DependentComponent.insert(Dependent(1, "abc", "xyz", Some(12)))
  val insertDependent2 = DependentComponent.insert(Dependent(2, "def", "xyz", None))
  val deleteDependent1 = DependentComponent.deleteById(1)
  val updateDependent1 = DependentComponent.updateNameById(2,"Mansi")
 // val upsertProject1 = ProjectComponent.upsert(Project(3, "java"))
  val upsertDependent1 = DependentComponent.upsert(Dependent(3, "sonam","abc",Some(20)))
  val selectAllEmployees = Await.result(EmployeeComponent.getAll(), 10000 second)
  val selectAllDependents = Await.result(DependentComponent.getAll(), 10000 second)
 // val selectAllProjects = Await.result(ProjectComponent.getAll(), 10000 second)
  val searchEmployee = Await.result(EmployeeComponent.search(2), 10000 second)
  println("deleted")
  println("All employees: "+selectAllEmployees )
  println("All employees: "+selectAllDependents )
//  println("All employees: "+selectAllProjects )
  println("Searched Employee"+ searchEmployee)
  val combined = Await.result(ProjectComponent.combinedActions(Project(4,"Slick"), Project(5,"Play")), 10000 second)
  //val res1 = combined.map(res => s"$res inserted")

  println("Result: "+combined+ " rows inserted")
  Thread.sleep(20000)

  //val res = insertRes.map(res => )
}
