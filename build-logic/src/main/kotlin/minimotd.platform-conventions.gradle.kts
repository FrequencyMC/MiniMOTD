plugins {
  id("minimotd.base-conventions")
  id("net.kyori.indra.git")
}

version = (version as String)
  .run { if (this.endsWith("-SNAPSHOT")) "$this+${lastCommitHash()}" else this }

val platformExtension = extensions.create<MiniMOTDPlatformExtension>("miniMOTDPlatform", project)

tasks {
  val copyJar = register<FileCopyTask>("copyJar") {
    fileToCopy.set(platformExtension.jarTask.flatMap { it.archiveFile })
    destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
  }
  build {
    dependsOn(copyJar)
  }
}
