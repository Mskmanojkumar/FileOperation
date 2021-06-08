import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class File_Operations {
	Scanner sc = new Scanner(System.in);
	File dir = new File("D:\\");
	List<File> files = new ArrayList<>();
	public void operation() {

		System.out.println("\t\t\tWelcome to Lockers Pvt. Ltd.");
		System.out.println("\t\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Project Name : LockedMe.com");
		System.out.println("Developed by : MANOJ KUMAR K\n");
		
		int i = 0;
		do {
			System.out.println("1.List Floder / Files ");
			System.out.println("2.File Adoptions(Add, Delete and Search)");
			System.out.println("3.Close the Application \n");
			System.out.println("Enter the choice :");
			String c = sc.nextLine();
			switch (c) {
			case "1":
				System.out.println("The Files in the Selective Directory Are:");
				list_Files();
				System.out.println("Do you Want to Continue Y/N:");
				String d = sc.nextLine();
				if (d.equals("n")||d.equals("N"))
					i = 1;
				break;
			case "2":
				System.out.println("Now you Can Add, Delete or Search a specific Folder");
				adoption();
				break;
			case "3":
				i = 1;
				break;
			default:
				System.out.println("Invalid Option: Please Type the Valid Option BW 1-3");
				break;
			}
		} while (i == 0);
		System.out.println("Thank You");
	}

	public void list_Files() {
		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				return !file.isDirectory();
			}
		};
		File[] files = dir.listFiles();
		files = dir.listFiles(fileFilter);
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2) {
				return (f1).getName().toLowerCase().compareTo((f2).getName().toLowerCase());
			}

		});

		for (File each : files) {
			System.out.println(each.getName());

		}

	}

	void adoption() {
		int i = 0;
		while (i == 0) {
			System.out.println("\ta. Add a New File");
			System.out.println("\tb. Delete a File");
			System.out.println("\tc. Search a File");
			System.out.println("\td. Go to Main Menu");
			System.out.println("\nEnter the choice :");
			String c = sc.nextLine();
			switch (c) {
			case "a":
				add_File();
				
				break;
			case "b":
				delete_File();
				break;
			case "c":
				search_File();
				break;
			case "d":
				i = 1;
				break;
			default:
				System.out.println("Invalid Option: Please Type the Valid Option BW A-D");
				break;
			}
		}
	}

	void add_File() {
		System.out.println("\tEnter the new File name : ");
		String newFileName = sc.nextLine();
		File newFile = new File(dir + newFileName);
		try {
			if (newFile.createNewFile()) {
				System.out.println("File created: " + newFile.getName());
			} else
				System.out.println("File already exists.\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("New File is created");
	}

	void delete_File() {
		
		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				return !file.isDirectory();
			}
		};

		for(File each:dir.listFiles(fileFilter)){
			files.add(each);
		}
		System.out.println("\nEnter the file you want to delete : ");
		String search = sc.nextLine();
		boolean isDeleted = false;

		for (File each : files) {
			if (each.getName().equals(search)) {
				each.delete();
				isDeleted = true;
				break;

			}
		}

		if (isDeleted)
			System.out.println("File Deleted\n");
		else
			System.out.println("File not found\n");

	}

	void search_File() {
		FileFilter fileFilter = new FileFilter() {
			@Override
			public boolean accept(File file) {
				return !file.isDirectory();
			}
		};
		for(File each:dir.listFiles(fileFilter)){
			files.add(each);
		}
		System.out.println("\nEnter the file you want to Searching : ");
		String search = sc.nextLine();
		boolean issearch = false;

		for (File each : files) {
			if (each.getName().equals(search)) {
				System.out.println(each.getName() + "  file Found\n");
				issearch = true;
				break;

			}
		}
		if (issearch == false)
			System.out.println("The file you are Searching for does not exit\n");
	}
};
