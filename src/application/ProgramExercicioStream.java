package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class ProgramExercicioStream {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Entre com o caminho do arquivo: ");
		String caminho = scan.next();
		System.out.print("Entre com o salario: ");
		double salario = scan.nextDouble();
		
		try(BufferedReader br = new BufferedReader(new FileReader(caminho))) {
			
			List<Employee> list = new ArrayList<>();
			String line = br.readLine();
			while(line != null) {
				String[] fields = line.split(",");
				list.add(new Employee(fields[0], fields[1],Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> emails = list.stream()
					.filter(e -> e.getSalary() > salario)
					.map(e -> e.getEmail())
					.sorted(comp)
					.collect(Collectors.toList());
			
			emails.forEach(System.out::println);
			
			double soma = list.stream()
					.filter(e -> e.getName().charAt(0) == 'M')
					.map(e -> e.getSalary())
					.reduce(0.0, (x,y) -> x + y);
			
			System.out.println("A soma do salario de pessoas que nome começa com a letra 'M': " + String.format("%.2f", soma) );
					
			
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		scan.close();
	}

}
