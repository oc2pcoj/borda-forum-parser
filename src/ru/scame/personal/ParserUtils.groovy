package ru.scame.personal

import java.net.URL;

class ParserUtils {

	def static String urlToUtfString(URL url) {
		def res = new BufferedReader(new InputStreamReader(url.openStream(), 'windows-1251'));
		byte[] utf8Bytes = res.getText().getBytes('UTF-8');
		res.close()
		return new String(utf8Bytes, 'UTF-8');
	}
}
