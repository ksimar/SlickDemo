import com.example.ProjectComponent

import scala.concurrent.Await
import scala.concurrent.duration._
/**
  * Created by simar on 16/3/17.
  */
object Slick extends App {

  val projectComponent = Await.result(ProjectComponent.joinEmployee, 10000.second)
  println("project lesft join: " + projectComponent)

}
