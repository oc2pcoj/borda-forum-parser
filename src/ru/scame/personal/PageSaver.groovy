package ru.scame.personal
import java.util.regex.Pattern;
class PageSaver {
	static final String WORK_PATH = '/';

	public static String generateValidNameForLocal(String linkForWeb) {
		return linkForWeb.replaceFirst('/\\?', '') + '.html'
	}

	def savePage(String pageBody, String pageTitle, listOfLink) {
		String validPageBody = validateUrlsInBody(pageBody, listOfLink);
		writeToFile(WORK_PATH, pageTitle, '.html', validPageBody)
	}

	def savePageOfSubTheme(def pageDescriptionList, String pageBody, def otherPagesDescriptionList, def themesUrlsDescription) {
		String validPageBody = validateUrlsInBody(pageBody, otherPagesDescriptionList)
		validPageBody = validateUrlsInBody(validPageBody, themesUrlsDescription)
		writeToFile(WORK_PATH, generateValidNameForLocal(pageDescriptionList[Constants.INDEX_FOR_LINK_IN_LIST]), '', validPageBody)
	}

	private String validateUrlsInBody(String body, def urlsDescription) {
		String validBody = body;
		urlsDescription.each { pageDescription ->
			String pureString = ((String)pageDescription[Constants.INDEX_FOR_LINK_IN_LIST])
			validBody = validBody.replace(pureString, PageSaver.generateValidNameForLocal(pageDescription[Constants.INDEX_FOR_LINK_IN_LIST]))
		}
		return validBody;
	}

	private void writeToFile(def directory, def fileName, def extension, def body) {
		new File("$directory/$fileName$extension").withWriter { out ->
			out.println body
		}
	}
}
