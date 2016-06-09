package me.yuanqingfei.transfer.mapper

import me.yuanqingfei.transfer.pojo.Transfer
import org.apache.ibatis.annotations._
import org.apache.ibatis.mapping.ResultSetType
import org.apache.ibatis.session.RowBounds
import org.springframework.stereotype.Component

/**
  * Created by aaron on 16-5-21.
  */
@Mapper
trait TransferMapper{

  @Select(Array("select * from transfer where id = #{id}"))
  def getTransfer(@Param("id") id: Int): Transfer

  @Select(Array("select * from transfer"))
  @Options(resultSetType=ResultSetType.SCROLL_INSENSITIVE)
  def getAll(rowBounds: RowBounds): java.util.List[Transfer]

  @Insert(Array("insert into transfer(doctor, sender, receiver) values(#{doctor}, #{sender}, #{receiver})"))
  @Options(useGeneratedKeys=true, keyProperty="id")
  def insertTransfer(transfer: Transfer)

  @Update(Array("update transfer set doctor=#{transfer.doctor}, sender=#{transfer.sender}, receiver=#{transfer.receiver} where id=#{id}"))
  def updateTransfer(@Param("id") id: Int, @Param("transfer")transfer: Transfer): Int

  @Delete(Array("delete from transfer where id=#{id}"))
  def deleteTransfer(@Param("id") id: Int)
}
