package me.yuanqingfei.transfer.rest

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import me.yuanqingfei.transfer.pojo.{Transfer, TransferList}
import me.yuanqingfei.transfer.service.TransferService
import org.scalatra.ScalatraServlet
import org.scalatra.scalate.ScalateSupport
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
  * Created by aaron on 16-5-28.
  */
@Component
class TransferServlet @Autowired()(transferService: TransferService) extends ScalatraServlet with ScalateSupport{

  get("/transfers"){
    val transferList = new TransferList
    val orginalList = transferService.getAll
    transferList.setTotal(orginalList.size)
    transferList.setResults(orginalList)
    JSON.toJSONString(transferList, SerializerFeature.PrettyFormat)
  }

  get("/transfers/:id") {
    val transfer = transferService.getTransfer(params("id"))
    JSON.toJSONString(transfer, SerializerFeature.PrettyFormat)
  }

  post("/transfers"){
    val jsonString = request.body
    val transfer = JSON.parseObject(jsonString, classOf[Transfer])
    val created = transferService.insertTransfer(transfer)
    JSON.toJSONString(created, SerializerFeature.PrettyFormat)
  }

  post("/transfers/:id"){
    val jsonString = request.body
    val transfer = JSON.parseObject(jsonString, classOf[Transfer])
    val created = transferService.insertTransfer(transfer)
    JSON.toJSONString(created, SerializerFeature.PrettyFormat)
  }

  put("/transfers/:id"){
    val jsonString = request.body
    val transfer = JSON.parseObject(jsonString, classOf[Transfer])
    val updated = transferService.updateTransfer(params("id"), transfer)
    JSON.toJSONString(updated, SerializerFeature.PrettyFormat)
  }

  delete("/transfers/:id"){
    transferService.deleteTransfer(params("id"))
    "{\"message\": \"delete successfully!\"}"
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound()
  }

}
