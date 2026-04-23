package algstudent.s6;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BinPackingBacktrackingTests {
	private int capacity;
	private List<Integer> sizes;
	
	@Test
	public void test00() {
		loadData("src/algstudent/s6/test00.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(4, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test01() {
		loadData("src/algstudent/s6/test01.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(1, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test02() {
		loadData("src/algstudent/s6/test02.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(8, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test03() {
		loadData("src/algstudent/s6/test03.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(2, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test04() {
		loadData("src/algstudent/s6/test04.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(10, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test05() {
		loadData("src/algstudent/s6/test05.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(14, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test06() {
		loadData("src/algstudent/s6/test06.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(7, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test07() {
		loadData("src/algstudent/s6/test07.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(8, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test08() {
		loadData("src/algstudent/s6/test08.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(16, problem.getBinsNeededSolution());
	}
	
	@Test
	public void test09() {
		loadData("src/algstudent/s6/test09.txt");		
		BinPackingBacktracking problem = new BinPackingBacktracking(this.capacity, this.sizes);
		problem.printData();
		problem.backtracking();
		problem.printSolution();
		assertEquals(33, problem.getBinsNeededSolution());
	}

	private void loadData(String file) {
		this.sizes = new ArrayList<Integer>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			this.capacity = Integer.valueOf(reader.readLine());		
			for (String s : reader.readLine().split(" ")) {
				this.sizes.add(Integer.valueOf(s));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
