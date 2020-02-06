package com.zekelabs.microserviceskafka;


import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.zekelabs.microserviceskafka.pojo.SalesData;
import com.zekelabs.microserviceskafka.util.ReadExcelFile;
import com.zekelabs.microserviceskafka.util.SalesStore;

@RestController
@RequestMapping("/sales")
public class SalesReportService {
	
	private static final DecimalFormat df2 = new DecimalFormat("#.##");

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WebClient.Builder webClientBuilder;
    
    @Autowired
	private KafkaTemplate<Object, Object> template;

	@GetMapping(path = "/report")
	public void publishReport() {
		printSales("UK");
		printSales("FR");
	}
	
	static void printSales(String country) {
		System.out.println("");
		System.out.println("Sale Report for Country: "+country);
		System.out.println("=================================");
		System.out.println("StockCode\t\tSale");
		System.out.println("=================================");
		if("UK".equalsIgnoreCase(country)) {
			SalesStore.getSalesUK().entrySet().forEach(entry-> {
				System.out.println(entry.getKey() +"\t\t\t" + df2.format(entry.getValue()));
			});
		} else if("FR".equalsIgnoreCase(country)) {
			SalesStore.getSalesFR().entrySet().forEach(entry-> {
				System.out.println(entry.getKey() +"\t\t\t" + df2.format(entry.getValue()));
			});
		}
	}
    
//
//    @RequestMapping("/{userId}")
//    public String getCatalog(@PathVariable("userId") String userId) {
//
//        /*
//    	UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
//
//        return userRating.getRatings().stream()
//                .map(rating -> {
//                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
//                })
//                .collect(Collectors.toList());
//        */
//    	runProducer();
//    	return "Done";
//    }
}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/