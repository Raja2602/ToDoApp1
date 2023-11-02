package todoapp;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class StartApp { // public void fileNameExits

	public static int addTaskMenu(Scanner sc1) {
		System.out.println("Press 1 to Add a Task");
		System.out.println("Press 2 to Edit a Task");
		System.out.println("Press 3 to Remove a Task");
		System.out.println("Press 4 to List the Tasks");
		System.out.println("Press 5 to Search");
		System.out.println("Press 6 to Go back");
		return sc1.nextInt();
	}

	public static int taskList(Scanner sc1) {
		System.out.println("Press 1 to list tasks by alphabetical listing by name");
		System.out.println("Press 2 to list tasks by due date");
		System.out.println("Press 3 to list tasks by created date");
		System.out.println("Press 4 to list tasks by longest time");
		System.out.println("Press 5 to exit");
		return sc1.nextInt();
	}

	public static void main(String[] args) {
		try {
			String fileName = "";
			Scanner sc1 = new Scanner(System.in);
			Scanner sc2 = new Scanner(System.in);
			LinkedHashSet<String> taskNames = new LinkedHashSet<>();
			int choice = -1;
			while (choice != 5) {
				System.out.println("Press 1 to Create Category");
				System.out.println("Press 2 to Load Category");
				System.out.println("Press 3 to Search");
				System.out.println("Press 4 to List");
				System.out.println("Press 5 to Exit");
				System.out.println("Enter your choice");
				choice = sc1.nextInt();
				switch (choice) {
				case 1: {
					int TaskChoice = 90;
					System.out.println("Enter the name of the category");
					fileName = sc2.nextLine();
					if (BusinessLogic.checkIfCategoryExists(fileName)) {
						System.out.println("file added successfully");

						while (TaskChoice != 6) {
							TaskChoice = StartApp.addTaskMenu(sc1);
							switch (TaskChoice) {
							case 1: {
								TaskBean ts = new TaskBean();
								System.out.println("enter task name");
								String name = sc2.nextLine();

								if (!BusinessLogic.findUniqueTaskName(taskNames, name)) {
									System.out.println("enter unique task name");
									break;
								}
								ts.setName(name);
								System.out.println("enter desc");
								ts.setDesc(sc2.nextLine());
								System.out.println("enter cr_dt mm-dd-yyyy format");
								String date = sc2.nextLine();
								ts.setCr_dt(date);
								if (!BusinessLogic.dateFormatCheck(date)) {
									continue;
								}
								System.out.println("enter end_dt mm-dd-yyyy format");
								String endDate = sc2.nextLine();
								ts.setEnd_dt(endDate);
								if (!BusinessLogic.checkTwoDate(date, endDate)) {
									continue;
								}
								System.out.println("enter priority");
								ts.setPriority(sc2.nextLine());
								System.out.println("enter status");
								ts.setStatus(sc2.nextLine());
								System.out.println("enter comma separated tags");
								ts.setTags(sc2.nextLine());
								if (BusinessLogic.addTask(ts, fileName))
									System.out.println("wrote success");
								else
									System.out.println("no success wrote");
								break;
							}
							case 2: {
								System.out.println("enter the task name to edit");
								String taskName = sc2.nextLine();
								System.out.println("enter the catagory name");
								String catName = sc2.nextLine();
								TaskBean b = BusinessLogic.edit(catName, taskName);
								System.out.println(b);
								int editChoice = 20;
								while (editChoice != 7) {
									System.out.println("Press 1 to change description");
									System.out.println("Press 2 to cr_dt");
									System.out.println("Press 3 to change end date");
									System.out.println("Press 4 to change priority");
									System.out.println("Press 5 to change status");
									System.out.println("Press 6 to complete edit");
									System.out.println("Press 7 to goback");
									editChoice = sc1.nextInt();
									switch (editChoice) {
									case 1:
										System.out.println("enter desc");
										b.setDesc(sc2.nextLine());
										break;
									case 2:
										System.out.println("enter cr_dt");
										String date = sc2.nextLine();
										if (BusinessLogic.dateFormatCheck(date))
											b.setCr_dt(date);

										break;
									case 3:
										System.out.println("enter end date");
										String date2 = sc2.nextLine();
										if (BusinessLogic.dateFormatCheck(date2))
											b.setCr_dt(date2);

										break;
									case 4:
										System.out.println("enter priority");
										b.setPriority(sc2.nextLine());
										break;
									case 5:
										System.out.println("enter status");
										b.setStatus(sc2.nextLine());
										break;
									case 6:
										System.out.println("in line 146" + b);
										BusinessLogic.addEditedData(catName, b);
										break;
									}
								}
								break;
							}
							case 3: {
								System.out.println("enter the taskName");
								String taskName = sc2.nextLine();
								System.out.println("enter the catName");
								String catName = sc2.nextLine();
								BusinessLogic.delete(catName, taskName);
								break;
							}
							case 4: {
								int listOutChoice = 50;
								while (listOutChoice != 5) {
									listOutChoice = taskList(sc1);
									switch (listOutChoice) {
									case 1: {
										BusinessLogic.getData(new File(Const.PATH + "\\" + fileName + ".txt"));
										break;
									}
									case 2: {
										BusinessLogic
												.getDataBasedDueDate(new File(Const.PATH + "\\" + fileName + ".txt"));
										break;
									}
									case 3: {
										BusinessLogic.getDataBasedcreatedDate(
												new File(Const.PATH + "\\" + fileName + ".txt"));
										break;
									}
									case 4: {
										BusinessLogic.getDataBasedLongestTime(
												new File(Const.PATH + "\\" + fileName + ".txt"));
										break;
									}

									}
								}

							}
							}
						}
					} else
						System.out.println("this file is already exists");
					break;
				}
				case 2: {
					// System.out.println("pingo");
					System.out.println("enter the name of the catagory");
					String catagory = sc2.nextLine();
					int selection = 500;
					while (selection != 6) {
						selection = addTaskMenu(sc1);
						switch (selection) {

						case 1: {
							TaskBean ts = new TaskBean();
							System.out.println("enter task name");
							String name = sc2.nextLine();

							if (!BusinessLogic.findUniqueToAdd(catagory, name)) {
								System.out.println("enter unique task name");
								break;
							}
							ts.setName(name);
							System.out.println("enter desc");
							ts.setDesc(sc2.nextLine());
							System.out.println("enter cr_dt mm-dd-yyyy format");
							String date = sc2.nextLine();
							ts.setCr_dt(date);
							if (!BusinessLogic.dateFormatCheck(date)) {
								continue;
							}
							System.out.println("enter end_dt mm-dd-yyyy format");
							String endDate = sc2.nextLine();
							ts.setEnd_dt(endDate);
							if (!BusinessLogic.checkTwoDate(date, endDate)) {
								continue;
							}
							System.out.println("enter priority");
							ts.setPriority(sc2.nextLine());
							System.out.println("enter status");
							ts.setStatus(sc2.nextLine());
							System.out.println("enter comma separated tags");
							ts.setTags(sc2.nextLine());
							if (BusinessLogic.addTask(ts, catagory))
								System.out.println("task added successfully");
							else
								System.out.println("task has not been added");
							break;
						}
						case 2: {
							System.out.println("enter the task name to edit");
							String taskName = sc2.nextLine();
//					System.out.println("enter the catagory name");
//					String catName = sc2.nextLine();
							TaskBean b = BusinessLogic.edit(catagory, taskName);
							System.out.println("in line 106 in main()" + b);
							int editChoice = 20;
							while (editChoice != 7) {
								System.out.println("Press 1 to change description");
								System.out.println("Press 2 to cr_dt");
								System.out.println("Press 3 to change end date");
								System.out.println("Press 4 to change priority");
								System.out.println("Press 5 to change status");
								System.out.println("Press 6 to complete edit");
								System.out.println("Press 7 to exit");
								editChoice = sc1.nextInt();
								switch (editChoice) {
								case 1:
									System.out.println("enter desc");
									b.setDesc(sc2.nextLine());
									break;
								case 2:
									System.out.println("enter cr_dt");
									String date = sc2.nextLine();
									if (BusinessLogic.dateFormatCheck(date))
										b.setCr_dt(date);

									break;
								case 3:
									System.out.println("enter end date");
									String date2 = sc2.nextLine();
									if (BusinessLogic.dateFormatCheck(date2))
										b.setCr_dt(date2);
									break;
								case 4:
									System.out.println("enter priority");
									b.setPriority(sc2.nextLine());
									break;
								case 5:
									System.out.println("enter status");
									b.setStatus(sc2.nextLine());
									break;
								case 6:
									System.out.println("in line 146" + b);
									BusinessLogic.addEditedData(catagory, b);
									break;
								}
							}
							break;

						}
						case 3: {
							System.out.println("enter the taskName");
							String taskName = sc2.nextLine();
							BusinessLogic.delete(catagory, taskName);
							break;
						}
						case 4: {
							int listOutChoice = 50;
							while (listOutChoice != 5) {
								listOutChoice = taskList(sc1);
								switch (listOutChoice) {
								case 1: {
									BusinessLogic.getData(new File(Const.PATH + "\\" + catagory + ".txt"));
									break;
								}
								case 2: {
									BusinessLogic.getDataBasedDueDate(new File(Const.PATH + "\\" + catagory + ".txt"));
									break;
								}
								case 3: {
									BusinessLogic
											.getDataBasedcreatedDate(new File(Const.PATH + "\\" + catagory + ".txt"));
									break;
								}
								case 4: {
									BusinessLogic
											.getDataBasedLongestTime(new File(Const.PATH + "\\" + catagory + ".txt"));
									break;
								}

								}
							}
							break;
						}
						case 5: {
							System.out.println("enter the word to search");
							String name = sc2.nextLine();
							BusinessLogic.search(catagory, name);
							break;
						}
						}
					}
					break;
				}
				case 3: {
                   //search
					System.out.println("enter the word to search and get the catagory name");
					String name = sc2.nextLine();
					BusinessLogic.getListForSearch(name);
					break;
				}
				case 4: {
//				System.out.println("timer");
//				System.out.println("enter the catagory name");
//				String catagory = sc2.nextLine();
//				System.out.println("enter the word to search");
//				String name = sc2.nextLine();
//				BusinessLogic.search(catagory, name);
//				break;
					BusinessLogic.getList();
					break;
				}
				default: {
					System.out.println("pls choose valid choice");
				}
				}
			}
		} // try end
		catch (InputMismatchException e) {
			System.out.println("don't give any string value enter number only restart your application");
		}
	}
}
