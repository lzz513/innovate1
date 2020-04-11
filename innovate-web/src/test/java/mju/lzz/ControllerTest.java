package mju.lzz;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
 * @program: innovate
 * @description:
 * @author: linzhizhu
 * @create: 2020-02-02 20:52
 **/
@Slf4j
public class ControllerTest {


	public static void main(String[] args) throws InterruptedException {
		HashMap<String , String> mp = new HashMap<>();
		mp.put("a", null);
		System.out.println(mp.containsKey("a"));
	}

}

class A implements AutoCloseable{
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("end");
	}

	public A(){}

	@Override
	public void close() throws Exception {

	}
}
