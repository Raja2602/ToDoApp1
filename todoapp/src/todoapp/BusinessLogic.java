package todoapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class BusinessLogic {
	public static boolean checkIfCategoryExists(String files) {
		File file = new File(Const.PATH);
		File[] temp = file.listFiles();
		for (File tem : temp) { // System.out.println(tem.getName());
			if ((files.toUpperCase() + ".txt").equals(tem.getName()))
				return false;
		}
		file = new File(Const.PATH + "\\" + files.toUpperCase() + ".txt");
		try {
			file.createNewFile();
		} catch (IOException e) {

			e.printStackTrace();
			System.out.println("this file is already exists");
		}
		return true;
	}

	public static void search(String catname, String word) {
		BufferedReader br = null;
		try {
			ArrayList<TaskBean> al = new ArrayList<>();
			br = new BufferedReader(new FileReader(Const.PATH + "\\" + catname.toUpperCase() + ".txt"));
			String line;
			int totalOccurrences = 0;
			int descOccurrences = 0;
			int nameOccurrences = 0;
			int tagsOccurrences = 0;

			System.out.println("Matches found:");

			while ((line = br.readLine()) != null) {
				if (line.contains(word)) {
					String[] tokens = line.split(", ");
					TaskBean t = new TaskBean();
					for (String temp : tokens) {
						String[] keyValue = temp.split("=", 2);
						if (keyValue.length == 2) {
							String key = keyValue[0].trim();
							String value = keyValue[1].trim();
							switch (key) {
							case "name":
								if (value.contains(word)) {
									nameOccurrences++;
									System.out.println(value);
								}
								t.setName(value);
								break;
							case "desc":
								if (value.contains(word)) {
									descOccurrences++;
									System.out.println(value);
								}
								t.setDesc(value);
								break;
							case "cr_dt":
								t.setCr_dt(value);
								break;
							case "end_dt":
								t.setEnd_dt(value);
								break;
							case "priority":
								t.setPriority(value);
								break;
							case "status":
								t.setStatus(value);
								break;
							case "tags":
								if (value.contains(word)) {
									tagsOccurrences++;
									System.out.println(value);
								}
								t.setTags(value);
								break;
							}
						}
					}
					al.add(t);
					totalOccurrences++;
				}
			}

			System.out.println("Total number of occurrences: " + totalOccurrences);
			System.out.println("Number of occurrences in description: " + descOccurrences);
			System.out.println("Number of occurrences in name: " + nameOccurrences);
			System.out.println("Number of occurrences in tags: " + tagsOccurrences);
			for (TaskBean temp : al) {
				System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//  public static TaskBean edit(String catName,String taskName)
//  {
//	  ArrayList<TaskBean> al=new ArrayList();
//	  TaskBean t = new TaskBean();
//	  boolean taskFound = false;
//      BufferedReader br=null;
//	   try {
//		br=new BufferedReader(new FileReader(new File(Const.PATH + "\\" + catName.toUpperCase() + ".txt")));
//		String line;
//	    while ((line = br.readLine()) != null) {
//	       
//	    	String[] tokens = line.split(", ");
//	       
//	        for (String temp : tokens) {
//	            String[] keyValue = temp.split("=", 2); // Split at the first "="
//	            if (keyValue.length == 2) {
//	                String key = keyValue[0].trim();
//	                String value = keyValue[1].trim();
//	                if (key.equals("name") && value.equals(taskName)) {
//	                	taskFound = true;
//	                }
//	                switch (key) {
//	                    case "name":
//	                        t.setName(value);
//	                        break;
//	                    case "desc":
//	                        t.setDesc(value);
//	                        break;
//	                    case "cr_dt":
//	                        t.setCr_dt(value);
//	                        break;
//	                    case "end_dt":
//	                        t.setEnd_dt(value);
//	                        break;
//	                    case "priority":
//	                        t.setPriority(value);
//	                        break;
//	                    case "status":
//	                        t.setStatus(value);
//	                        break;
//	                    case "tags":
//	                        t.setTags(value);
//	                        break;
//	                }
//	              
//	            }
//	            
//	        }
//	        if (!taskFound) {
//	            System.out.println("Task not found.");
//	        }
//	       
//		}
//		return t;
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		//e.printStackTrace();
//		System.out.println("no task found");
//	}
//	   catch(IOException i)
//	   {
//		   i.printStackTrace();
//		   return t;
//	   }
//	   finally {
//		try {
//			if(br!=null)
//			{
//			br.close();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//  }
//	return t;
//  }
	public static TaskBean edit(String catName, String taskName) {
		TaskBean t = new TaskBean();
		boolean taskFound = false; // Flag to check if the task is found

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(Const.PATH + "\\" + catName.toUpperCase() + ".txt")));
			String line;

			while ((line = br.readLine()) != null) {
				if (line.contains("name=" + taskName)) {
					String[] tokens = line.split(", ");

					for (String temp : tokens) {
						String[] keyValue = temp.split("=", 2);

						if (keyValue.length == 2) {
							String key = keyValue[0].trim();
							String value = keyValue[1].trim();

							switch (key) {
							case "name":
								t.setName(value);
								break;
							case "desc":
								t.setDesc(value);
								break;
							case "cr_dt":
								t.setCr_dt(value);
								break;
							case "end_dt":
								t.setEnd_dt(value);
								break;
							case "priority":
								t.setPriority(value);
								break;
							case "status":
								t.setStatus(value);
								break;
							case "tags":
								t.setTags(value);
								break;
							}
						}
					}

					taskFound = true;
				}
			}

			if (!taskFound) {
				System.out.println("Task not found.");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException i) {
			i.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return t;
	}

	public static void delete(String catName, String task) {
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {
			ArrayList<String> lines = new ArrayList<>();
			br = new BufferedReader(new FileReader(Const.PATH + "\\" + catName.toUpperCase() + ".txt"));
			String line;

			while ((line = br.readLine()) != null) {
				if (!line.contains(task)) {
					lines.add(line);
				}
			}

			bw = new BufferedWriter(new FileWriter(new File(Const.PATH + "\\" + catName.toUpperCase() + ".txt")));

			for (String temp : lines) {
				bw.write(temp);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void addEditedData(String catName, TaskBean ts) {
		BufferedWriter bw = null;
		delete(catName, ts.getName());
		try {
			bw = new BufferedWriter(new FileWriter(new File(Const.PATH + "\\" + catName.toUpperCase() + ".txt"), true));

			// Ensure that the TaskBean is formatted as key-value pairs
			bw.write("name=" + ts.getName() + ", desc=" + ts.getDesc() + ", cr_dt=" + ts.getCr_dt() + ", end_dt="
					+ ts.getEnd_dt() + ", priority=" + ts.getPriority() + ", status=" + ts.getStatus() + ", tags="
					+ ts.getTags());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean addTask(TaskBean task, String catName) {
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(new File(Const.PATH + "\\" + catName + ".txt"), true));
			bf.write(task.toString());
			bf.write(System.lineSeparator());
			System.out.println("writed" + task.toString());
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;

	}

	public static boolean findUniqueTaskName(LinkedHashSet<String> l, String name) {
		if (!l.add(name))
			return false;
		else
			return true;
	}

	public static void getData(File file) {
		TreeSet<String> tree = new TreeSet();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = br.readLine();
			if(line==null)
				System.out.println("you don't have any task.exit and add task");
			while (line != null) {
				tree.add(line);
				line = br.readLine();
			}
			for (String temp : tree) {
				System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void getDataBasedDueDate(File file) {
		TreeSet<TaskBean> tree = new TreeSet<TaskBean>(new DueDateComparator());
		BufferedReader br = null;
		try { int times=0;
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				times++;
				String[] tokens = line.split(", ");
				TaskBean t = new TaskBean();
				for (String temp : tokens) {
					String[] keyValue = temp.split("=", 2); // Split at the first "="
					if (keyValue.length == 2) {
						String key = keyValue[0].trim();
						String value = keyValue[1].trim();
						switch (key) {
						case "name":
							t.setName(value);
							break;
						case "desc":
							t.setDesc(value);
							break;
						case "cr_dt":
							t.setCr_dt(value);
							break;
						case "end_dt":
							t.setEnd_dt(value);
							break;
						case "priority":
							t.setPriority(value);
							break;
						case "status":
							t.setStatus(value);
							break;
						case "tags":
							t.setTags(value);
							break;
						}
					}
				}
				tree.add(t);
			}
			if(times==0)
				System.out.println("you don't have any task.exit and add task");
			for (TaskBean temp : tree) {
				System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void getDataBasedcreatedDate(File file) {
		TreeSet<TaskBean> tree = new TreeSet<TaskBean>(new CreatedDateComparator());
		BufferedReader br = null;
		try {
			int times=0;
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				times++;
				String[] tokens = line.split(", ");
				TaskBean t = new TaskBean();
				for (String temp : tokens) {
					String[] keyValue = temp.split("=", 2); // Split at the first "="
					if (keyValue.length == 2) {
						String key = keyValue[0].trim();
						String value = keyValue[1].trim();
						switch (key) {
						case "name":
							t.setName(value);
							break;
						case "desc":
							t.setDesc(value);
							break;
						case "cr_dt":
							t.setCr_dt(value);
							break;
						case "end_dt":
							t.setEnd_dt(value);
							break;
						case "priority":
							t.setPriority(value);
							break;
						case "status":
							t.setStatus(value);
							break;
						case "tags":
							t.setTags(value);
							break;
						}
					}
				}
				tree.add(t);
			}
			if(times==0)
			{
				System.out.println("you don't have any task.exit and add task");
			}
			for (TaskBean temp : tree) {
				System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void getDataBasedLongestTime(File file) {
		TreeSet<TaskBean> tree = new TreeSet<TaskBean>(new longestTimeComparator());
		BufferedReader br = null;
		try {
			int times=0;
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(", ");
				TaskBean t = new TaskBean();
				for (String temp : tokens) {
					String[] keyValue = temp.split("=", 2); // Split at the first "="
					if (keyValue.length == 2) {
						String key = keyValue[0].trim();
						String value = keyValue[1].trim();
						switch (key) {
						case "name":
							t.setName(value);
							break;
						case "desc":
							t.setDesc(value);
							break;
						case "cr_dt":
							t.setCr_dt(value);
							break;
						case "end_dt":
							t.setEnd_dt(value);
							break;
						case "priority":
							t.setPriority(value);
							break;
						case "status":
							t.setStatus(value);
							break;
						case "tags":
							t.setTags(value);
							break;
						}
					}
				}
				tree.add(t);
			}
			if(times==0)
				System.out.println("you don't have any task.exit and add task");
			for (TaskBean temp : tree) {
				System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static boolean dateFormatCheck(String date) {
		String[] str = date.split("-");
		try {
			int month = Integer.parseInt(str[0]);
			int day = Integer.parseInt(str[1]);
			int year = Integer.parseInt(str[2]);
			SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy");
			Date d = new Date();
			try {

				d = sd.parse(date);
				Date currentDate = new Date();
				d.setTime(24000);
				if (currentDate.before(d)) {
					System.out.println("date cannot be past");
					return false;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("date cannot be a String");
			return false;
		}

	}

	public static boolean checkTwoDate(String startDate, String endDate) {
		String[] str = endDate.split("-");
		try {
			int month = Integer.parseInt(str[0]);
			int day = Integer.parseInt(str[1]);
			int year = Integer.parseInt(str[2]);
			SimpleDateFormat sd = new SimpleDateFormat("MM-dd-yyyy");
			SimpleDateFormat sd2 = new SimpleDateFormat("MM-dd-yyyy");
			Date end = new Date();
			Date start = new Date();
			try {
				end = sd.parse(endDate);
				start = sd2.parse(startDate);
				// Date currentDate=new Date();
				if (end.before(start)) {
					System.out.println("end date cannot come before start date");
					return false;
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("date cannot be a String");
			return false;
		}

	}

	public static boolean findUniqueToAdd(String catName, String taskName) {
		HashSet<String> hash = new HashSet<String>();
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(new File(Const.PATH + "\\" + catName + ".txt")));
			String line = "";
			while (line != null) {
				try {
					line = bf.readLine();
					if (line != null) {
						String[] str = line.split(", ");
						for (String temp : str) {
							String[] st = temp.split("=");
							if (st.length == 2) {
								hash.add(st[1]);
							}
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (!hash.add(taskName))
				return false;
			else
				return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void getList() {
		File file = new File(Const.PATH);
		String[] names = file.list();
		for (String temp : names) {
			System.out.println(temp);
		}
	}
//	public static void getListForSearch(String name) {
//		File file = new File(Const.PATH);
//		File[] names = file.listFiles();
//		for (File temp : names)
//		{
//			if(temp.getName().contains(name))
//			{
//			System.out.println(temp.getName());
//			}
//		}
//	}
	public static void getListForSearch(String name) {
	    int times=0;
		File directory = new File(Const.PATH);
	    File[] files = directory.listFiles();
	   // System.out.println("*******************"+files);
	    if (files != null) {
	        for (File file : files) {
	        	//System.out.println("file====="+file.getName());
	            if (file.getName().startsWith(name.toUpperCase()) || file.getName().contains(name.toUpperCase())) {
	            	//System.out.println("entered 718");
	            	times++;
	                System.out.println(file.getName());
	            }
	        }
	    }
	    if(times==0)
	    	System.out.println("you don't have any catagory with this name");
	}

}
