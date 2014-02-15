package ru.scame.personal

import javax.swing.text.html.HTMLEditorKit.HTMLFactory.BodyBlockView;

class OsoSubThemeParser {
	def listOfMainThemes
	public OsoSubThemeParser(def _listOfMainThemes) {
		listOfMainThemes = _listOfMainThemes;
	}
	
	def parseSubThemes() {
		
		String utfBody
		SubThemeLinkGetter linkGetter = new SubThemeLinkGetter()
		
		
		def subTheme = listOfMainThemes[0]
		String urlForSubMenu = subTheme[Constants.INDEX_FOR_HTTP_LINK_IN_LIST]
		String subThemeBodyFirstPage = ParserUtils.urlToUtfString(urlForSubMenu.toURL())
		
		def subThemePagesDescription = linkGetter.getPagesForSubTheme(subThemeBodyFirstPage, subTheme[Constants.INDEX_FOR_TEXT_IN_LIST])
		
		subThemePagesDescription.each { pageDescriptionList ->
			pageDescriptionList[Constants.INDEX_FOR_TEXT_IN_LIST] = ParserUtils.urlToUtfString(pageDescriptionList[Constants.INDEX_FOR_HTTP_LINK_IN_LIST].toString().toURL())
		}
		
		PageSaver pageSaver = new PageSaver()
		
		linkGetter.initPatterns()
		def themesUrlDescriptionList = linkGetter.getLinks(subThemeBodyFirstPage)
		pageSaver.savePageOfSubTheme(subTheme, subThemeBodyFirstPage, subThemePagesDescription, themesUrlDescriptionList)
		subThemePagesDescription.each { pagesDescr ->
			def themesUrlDescrForPage = linkGetter.getLinks(pagesDescr[Constants.INDEX_FOR_TEXT_IN_LIST].toString())
			pageSaver.savePageOfSubTheme(pagesDescr, pagesDescr[Constants.INDEX_FOR_TEXT_IN_LIST].toString(), subThemePagesDescription, themesUrlDescrForPage)
			themesUrlDescriptionList += themesUrlDescrForPage
		}

		themesUrlDescriptionList.each { descr ->
			println descr[Constants.INDEX_FOR_TEXT_IN_LIST]
			println descr[Constants.INDEX_FOR_HTTP_LINK_IN_LIST]
			
		}
	}
	
}
