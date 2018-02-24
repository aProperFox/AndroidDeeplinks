
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.ui.Messages
import java.io.IOException

class DeeplinkTest : AnAction() {

  companion object {
    private const val DEEPLINK_COMMAND_PRE = "adb shell am start -W -a android.intent.action.VIEW -d"

    private fun buildDeeplink(text: String): String =
        "$DEEPLINK_COMMAND_PRE \"$text\""
  }

  override fun actionPerformed(event: AnActionEvent) {
    val project = event.getData(PlatformDataKeys.PROJECT)
    val txt = Messages.showInputDialog(project, "Enter deeplink URL", "Enter Deeplink URL", Messages.getQuestionIcon())
    if (txt != null) {
      try {
        Runtime.getRuntime().exec(buildDeeplink(txt))
      } catch (e: IOException) {
        println("exception happened - here's what I know: ")
        e.printStackTrace()
      }
    }
  }
}