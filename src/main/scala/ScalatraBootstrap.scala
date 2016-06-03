import javax.servlet.ServletContext

import me.yuanqingfei.transfer.MyRestApplication
import me.yuanqingfei.transfer.rest.TransferServlet
import org.scalatra.LifeCycle
import org.springframework.context.annotation.AnnotationConfigApplicationContext

/**
  * Created by aaron on 16-6-3.
  */
class ScalatraBootstrap extends LifeCycle {


  override def init(context: ServletContext) {

    val springContext = new AnnotationConfigApplicationContext
    springContext.register(classOf[MyRestApplication])
    springContext.refresh()

    context.mount(springContext.getBean[TransferServlet]("transferServlet", classOf[TransferServlet]), "/api/*")
  }
}
