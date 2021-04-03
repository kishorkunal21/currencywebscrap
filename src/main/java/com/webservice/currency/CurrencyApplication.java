package com.webservice.currency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.webservice.currency.domain.Airline;
import com.webservice.currency.domain.XE;

@SpringBootApplication

public class CurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyApplication.class, args);
		// readJson();
		webscraper();
	}

	static void webscraper() {
		WebClient client = new WebClient();
		String searchUrl = "https://www.x-rates.com/table/?from=INR&amount=1";
		try {
			client.getOptions().setThrowExceptionOnScriptError(false);
			client.getOptions().setCssEnabled(false);
			client.getOptions().setJavaScriptEnabled(false);

			HtmlPage page = client.getPage(searchUrl);
			// System.out.println("\n\n" + page.asXml());

			Path path = Paths.get("D:/Currency.xml");
			if (!Files.exists(path))
				path = Files.createFile(Paths.get("D:/Currency.xml"));
			System.out.println("Writing to file - ");
			Files.write(path, page.asXml().getBytes());

			List<HtmlTable> table = page.getByXPath("//table[@class='ratesTable']");
			List<HtmlTableRow> row = table.get(0).getRows();

			List<XE> xes = new ArrayList<XE>();
			int index = 0;
			for (HtmlTableRow d : row) {
				if (index > 0)
					xes.add(new XE(d.getCell(0).asText(), Double.parseDouble(d.getCell(1).asText()),
							Double.parseDouble(d.getCell(2).asText())));

				index++;
			}

			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File("d:\\currency.json"), xes);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void readJson() {

		// create object mapper
		ObjectMapper mapper = new ObjectMapper();

		Airline airline;
		try {
			airline = mapper.readValue(new File("D:\\etc\\airline.json"), Airline.class);
			System.out.println(airline.getAirline());
			System.out.println(airline.getPassanger().getName() + " " + airline.getPassanger().getLocation());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// read json file and map/convert java to POJO

		// print airline name

	}

}
