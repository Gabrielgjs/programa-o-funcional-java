package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProgramExercicioStream2 {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com o caminho do arquivo: ");
		String caminho = scan.next();
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			
			List<Product> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Product(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			
			//Achar média dos preços dos produtos!
			double media = list.stream()
					.map(p -> p.getPrice())
					.reduce(0.0, (x,y) -> x + y) /list.size();
			
			System.out.println("O preço médio é: " + String.format("%.2f", media));
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> nomes = list.stream()
					.filter(p -> p.getPrice() < media) //filtra os elementos com valor abaixo da média
					.map(p -> p.getName())//acessa o nome dos elementos do predicado acima 
					.sorted(comp.reversed())//coloca os nomes em ordem decrescente
					.collect(Collectors.toList());
					
			nomes.forEach(System.out::println);		
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		scan.close();
	}

}
