package mju.lzz.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-04-20 13:06
 **/
@Slf4j
public class Tire {

	class TireNode{

		Character ch;
		boolean last;
		HashMap<Character, TireNode> map;

		TireNode(Character ch) {
			this.ch = ch;
			last = false;
			map = new HashMap<>();
		}

		public boolean hasLetter(Character ch) {
			return map.get(ch) != null;
		}

		public TireNode get(Character ch) {
			return map.get(ch);
		}

		public void setIsLast(boolean b) {
			this.last = true;
		}

		public boolean isLast() {
			return last;
		}

		public void append(Character ch, TireNode next) {
			map.put(ch, next);
		}
	}

	private TireNode root;
	public static final String TIHUANG = "***";


	public Tire(String file){
		root = createByFile(file);
	}

	public String replace(String text) {
		StringBuilder sb = new StringBuilder();
		int b = 0, e = 0;
		TireNode now = root;
		while (b < text.length()){
			Character ch = text.charAt(e);
			TireNode next = now.get(ch);
			if (next == null) {
				sb.append(text.charAt(b));
				b++;
				e = b;
				now = root;
			} else {
				now = next;
				if (now.isLast()) {
					sb.append(TIHUANG);
					b = e+1;
					e = b;
				} else {
					e++;
				}
			}
		}
		return sb.toString();
	}

	private TireNode createByFile(String path)  {
		TireNode root = new TireNode(' ');
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(path)))){
			String line = null;
			while ((line = reader.readLine()) != null) {
				addWord(root, line);
			}
		}
		 catch (IOException e) {
			log.info("e={}", e);
		}
		return root;
	}

	private void addWord(TireNode root, String line) {
		TireNode now = root;
		for (int i = 0; i < line.length(); i++) {
			Character ch = line.charAt(i);
			TireNode next = now.get(ch);
			if (next == null) {
				next = new TireNode(ch);
			}
			now.append(ch, next);
			now = next;
			if (i == line.length()-1) {
				now.setIsLast(true);
			}
		}
	}

}
