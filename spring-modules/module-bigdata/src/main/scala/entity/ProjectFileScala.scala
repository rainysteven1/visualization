package entity

class ProjectFileScala {

  private var fileName: String = ""

  private var fileUrl: String = ""

  def this(fileName: String, fileUrl: String) {
    this()
    this.fileName = fileName
    this.fileUrl = fileUrl
  }
}
