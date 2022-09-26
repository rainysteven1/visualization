package service

import entity.ProjectFileScala
import org.apache.spark.sql.SparkSession

import java.util

object AnalysisProject {
  def analysis(sparkSession: SparkSession, projectFileScalaMap: util.Map[String, util.List[ProjectFileScala]]) = {
    val spark = sparkSession
    val structureFileScalaList = projectFileScalaMap.get("structure_files")
    val countFileScalaList = projectFileScalaMap.get("count_files")
    val relationFileScalaList = projectFileScalaMap.get("relation_files")
    structureFilesMatchRelationFiles(structureFileScalaList, relationFileScalaList)
  }

  def structureFilesMatchRelationFiles(structureFiles: util.List[ProjectFileScala], relationFiles: util.List[ProjectFileScala]): Unit = {

  }
}
