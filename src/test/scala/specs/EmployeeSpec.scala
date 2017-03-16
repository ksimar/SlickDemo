package specs

import com.example.{Employee, EmployeeComponent}
import dBConnection.DBCompForTesting
import org.scalatest.AsyncFunSuite

class EmployeeSpec extends AsyncFunSuite with EmployeeComponent with DBCompForTesting {


  test("It should insert a row") {
    val emp = Employee(5, "Jatin", 20)
    insert(emp).map(x => assert(x == 1))
  }

  test("It should delete a row") {
    deleteById(4).map(x => assert(x == 1))
  }

  test("It should update name ") {
    updateNameById(3, "Suraj").map(x => assert(x == 1))
  }

  test("It should insert or update an employee") {
    upsert(Employee(4, "sonam", 10)).map(x => assert(x == 1))
  }

  test("It should retrieve all records") {
    getAll().map(list => assert(list.size == 4))
  }

//  test("It should search a record") {
//    search(3).map()
//  }
//
}
