package com.antoniomasfanclub;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CLI {
    private final CRM crm;
    private final Scanner scanner;
    /**
     * We use this in order  to be able to display emojis in Windows terminals too
     */
    private final PrintWriter printer = new PrintWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8), true);

    public CLI() {
        crm = new CRM();
        scanner = new Scanner(System.in);
        populateCRM();
    }


    /**
     * The core method of the CRM, and the only public one. Creates an infinite loop that translates user input into
     * actions, and only ends when instructed to by the user,
     */
    public void startCRM() {
        printer.println(Colours.BACKGROUND_YELLOW + "@@@@@@@@@@@@ Welcome to the " + Colours.RED + "🍆Antonio Mas👼🏻 Fan Club CRM®️" + Colours.BLACK + "! @@@@@@@@@@@@" + Colours.RESET);
        boolean run = true;

        do {
            printCRMOptions();
            String[] userInput = scanner.nextLine().trim().toLowerCase().split("[ -]");
            try {
                switch (userInput[0]) {
                    case "new":
                        if (userInput[1].equals("lead")) {
                            createNewLead();
                            break;
                        }
                    case "lookup":
                        if (userInput[1].equals("lead")) {
                            printItem(() -> this.crm.getLead(Integer.parseInt(userInput[2])));
                            break;
                        }
                        if (userInput[1].equals("opportunity")) {
                            printItem(() -> this.crm.getOpportunity(Integer.parseInt(userInput[2])));
                            break;
                        }
                        if (userInput[1].equals("contact")) {
                            printItem(() -> this.crm.getContact(Integer.parseInt(userInput[2])));
                            break;
                        }
                        if (userInput[1].equals("account")) {
                            printItem(() -> this.crm.getAccount(Integer.parseInt(userInput[2])));
                            break;
                        }
                        printer.println("Could not understand your input, please try again using " + colourString(Colours.CYAN, "lead") + ", " + colourString(Colours.CYAN, "contact") + ", " + colourString(Colours.CYAN, "account") + " or " + colourString(Colours.CYAN, "opportunity") + " followed by the id");
                        break;
                    case "list":
                        if (userInput[1].equals("leads")) {
                            printList(this.crm.getLeads());
                            break;
                        }
                        if (userInput[1].equals("opportunities")) {
                            printList(this.crm.getOpportunities());
                            break;
                        }
                        if (userInput[1].equals("contacts")) {
                            printList(this.crm.getContacts());
                            break;
                        }
                        if (userInput[1].equals("accounts")) {
                            printList(this.crm.getAccounts());
                            break;
                        }
                        printer.println("Could not understand your input, please try again using " + colourString(Colours.CYAN, "leads") + ", " + colourString(Colours.CYAN, "contacts") + ", " + colourString(Colours.CYAN, "accounts") + " or " + colourString(Colours.CYAN, "opportunities") + ".");
                        break;
                    case "convert":
                        convertLead(userInput[1]);
                        break;
                    case "closed":
                        closeOpportunity(userInput);
                        break;
                    case "quit":
                        run = false;
                        break;
                    default:
                        printer.println("Sorry, I do not understand '" + colourString(Colours.YELLOW, String.join(" ", userInput)) + "'. Could you try again?");
                }
            } catch (Exception e) {
                printer.println("Your command seems to be unrecognisable or incomplete. Please try again.");
            }
        } while (run);
        printer.println("Quitting the CRM. " + colourString(Colours.YELLOW, "Have a great day!"));
    }

    /**
     * To avoid clogging the previous method, the options menu is extracted to an independent method.
     */
    private void printCRMOptions() {
        printer.println();
        printer.println("- To create a new lead, type '" + colourString(Colours.GREEN, Command.NEW_LEAD.toString()) + "' ");
        printer.println("- To see a specific lead, contact, account or opportunity, type '" + colourString(Colours.GREEN, Command.LOOKUP.toString()) + "' or the equivalent, followed by the "+colourString(Colours.GREEN, "item id"));
        printer.println("- To see all current leads, contacts, accounts or opportunities, type '" + colourString(Colours.GREEN, Command.LIST_LEADS.toString()) + "' or the equivalent");
        printer.println("- To convert a lead into an opportunity type '" + colourString(Colours.GREEN, Command.CONVERT.toString()) + "' followed by the " + colourString(Colours.GREEN, "lead id"));
        printer.println("- To close an opportunity, type '" + colourString(Colours.RED, Command.CLOSED_LOST.toString()) + "' or '" + colourString(Colours.GREEN, Command.CLOSED_WON.toString()) + "' followed by the " + colourString(Colours.GREEN, "opportunity id"));
        printer.println("- To quit the CRM, type '" + colourString(Colours.RED, Command.QUIT.toString()) + "' ");
    }

    /**
     * Creates a new lead using user console input to assign its values.
     */
    private void createNewLead() {
        Lead lead = new Lead();
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "👤 name") + ":", lead::setName);
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "🏢 company name") + ":", lead::setCompanyName);
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "☎️ phone number") + ":", lead::setPhoneNumber);
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "✉️ email") + ":", lead::setEmail);

        this.crm.addLead(lead);
        printer.println(colourString(Colours.GREEN, "Success!") + " Lead with ID " + colourString(Colours.CYAN, lead.getId() + "") + " was added to the leads list.");
    }

    /**
     * Converts an existing lead into an opportunity, generating a new account and contact in the process.
     *
     * @param key the ID of the lead instance to convert into an opportunity
     */
    private void convertLead(String key) {
        try {
            Lead lead = this.crm.getLead(Integer.parseInt(key));
            Contact contact = new Contact(lead);
            printer.println("Converting the following lead: " + lead);

            Opportunity opportunity = createOpportunity(contact);
            printer.println("\nOpportunity created: " + opportunity + "\n");

            Account account = createAccount(contact, opportunity);
            printer.println("\nAccount created: " + account + "\n");

            this.crm.deleteLead(Integer.parseInt(key));
            this.crm.addAccount(account);
            this.crm.addContact(contact);
            this.crm.addOpportunity(opportunity);

            printer.println("Completed lead conversion to opportunity\n");
        } catch (IllegalArgumentException e) {
            printer.println(colourString(Colours.RED, "Error") + " - " + e.getMessage() + "\n");
        }
    }

    /**
     * Creates a new Opportunity instance associated with a Contact instance.
     *
     * @param contact the Contact instance the new Opportunity should be associated with, based on the original Lead
     * @return the created Opportunity instance
     */
    private Opportunity createOpportunity(Contact contact) {
        Opportunity opportunity = new Opportunity();
        opportunity.setContact(contact);
        printer.println("Creating a new " + colourString(Colours.GREEN, "opportunity"));
        updateIntegerKey("Please input this opportunity's quantity", opportunity::setQuantity);
        updateEnumKey(Product.BOX, opportunity::setProduct, opportunity::getProduct);
        updateEnumKey(Status.OPEN, opportunity::setStatus, opportunity::getStatus);
        return opportunity;
    }

    /**
     * Creates a new Account instance associated with a Contact and an Opportunity instances.
     *
     * @param contact     the Contact instance the new Account should be associated with, based on the original Lead
     * @param opportunity the Opportunity instance the new Account should be associated with
     * @return the created Account instance
     */
    private Account createAccount(Contact contact, Opportunity opportunity) {
        Account account = new Account();
        printer.println("Creating the associated " + colourString(Colours.CYAN, "account"));
        account.addContact(contact);
        account.addOpportunity(opportunity);
        updateEnumKey(Industry.MEDICAL, account::setIndustry, account::getIndustry);
        updateStringKey("Please introduce this account's " + colourString(Colours.GREEN, "🇺🇳 country") + ":", account::setCountry);
        updateStringKey("Please introduce this account's " + colourString(Colours.CYAN, "🏬 city") + ":", account::setCity);
        updateIntegerKey("Please introduce this account's " + colourString(Colours.YELLOW, "👔 employee count") + ":", account::setEmployeeCount);
        return account;
    }

    /**
     * Reads user input to find an opportunity and close it as instructed
     *
     * @param userInput String array ideally with: [1] 'won' or 'lost' strings, and [2] associated Opportunity id.
     */
    private void closeOpportunity(String[] userInput) {
        try {
            if (userInput[1].equals("lost")) {
                this.crm.getOpportunity(Integer.parseInt(userInput[2])).setStatus(Status.CLOSED_LOST);
                printer.println("Opportunity closed as " + colourString(Colours.RED, "Lost"));
            } else if (userInput[1].equals("won")) {
                this.crm.getOpportunity(Integer.parseInt(userInput[2])).setStatus(Status.CLOSED_WON);
                printer.println("Opportunity closed as " + colourString(Colours.GREEN, "Won"));

            } else {
                printer.println("Opportunities must be marked as " + colourString(Colours.RED, "lost") + " or " + colourString(Colours.GREEN, "won") + " when closing.");
            }
        } catch (IllegalArgumentException e) {
            printer.println(e.getMessage());
        }
    }

    /**
     * Prints all the keys of any given Map onto the console.
     *
     * @param list Any kind of Map that you want to print on the console
     */
    private <T> void printList(Map<Integer, T> list) {
        if (list.keySet().size() == 0)
            printer.println("There are no items in this list.");
        for (int key : list.keySet()) {
            printer.println(list.get(key));
        }
    }

    /**
     * This method enables us to reuse the logic to print single object instances into the console
     *
     * @param itemGetter getter for the object instance you want to print
     */
    private void printItem(Callable itemGetter) {
        try {
            printer.println(itemGetter.call());
        } catch (Exception e) {
            printer.println(e.getMessage() + "\n");
        }
    }

    /**
     * This method takes a generic function, and runs it until it no longer throws an exception.
     *
     * @param message      The message shown in the console before running the update method
     * @param updateMethod A consumer, ideally a class setter.
     */
    private void updateGenericKey(String message, Runnable updateMethod) {
        boolean wasUpdatedSuccessfully = false;
        do {
            printer.println(message);
            try {
                updateMethod.run();
                wasUpdatedSuccessfully = true;
            } catch (IllegalArgumentException e) {
                printer.println(e.getMessage());
            }
        } while (!wasUpdatedSuccessfully);
    }

    /**
     * Enables a simple way to update String-based class instance properties from the console.
     *
     * @param message      The message shown in the console before running the update method
     * @param updateMethod A consumer, ideally a class setter, that accepts a String param.
     */
    private void updateStringKey(String message, Consumer<String> updateMethod) {
        updateGenericKey(message, () -> updateMethod.accept(scanner.nextLine()));
    }

    /**
     * Enables a simple way to update Integer-based class instance properties from the console.
     *
     * @param message      The message shown in the console before running the update method
     * @param updateMethod A consumer, ideally a class setter, that accepts a n Integer param.
     */
    private void updateIntegerKey(String message, Consumer<Integer> updateMethod) {
        updateGenericKey(message, () -> {
            boolean validInput = false;
            int nextInt = 0;
            do {
                if (scanner.hasNextInt()) {
                    nextInt = scanner.nextInt();
                    validInput = true;
                } else printer.println("Only integers are allowed as options, please try again.");
                scanner.nextLine();
            } while (!validInput);
            updateMethod.accept(nextInt);
        });
    }

    /**
     * This method takes an enum key and a class instance's property's getter and setter to provide a standarised way to update
     * enum based class properties.
     *
     * @param enumExample Any value for the enum of the class property you want to update
     * @param setter      Setter method to update the enum-based class property
     * @param getter      Getter method to check the enum-based class property
     */
    private <T extends Enum<T>> void updateEnumKey(Enum<T> enumExample, Consumer<T> setter, Supplier<T> getter) {
        do {
            int nextInt = 0;
            T[] enumValues = enumExample.getDeclaringClass().getEnumConstants();
            printer.println("Please enter the number of the product for this opportunity.");
            for (int i = 1; i <= enumValues.length; i++) {
                printer.println(colourString(Colours.CYAN, " " + i + "- ") + enumValues[i - 1]);
            }
            if (scanner.hasNextInt()) {
                nextInt = scanner.nextInt();
                scanner.nextLine();
            } else {
                printer.println("Only integers are allowed as options, please try again.");
                scanner.nextLine();
            }
            if (nextInt > 0 && nextInt <= enumValues.length) {
                setter.accept(enumValues[nextInt - 1]);
            }
        } while (getter.get() == null);
    }

    /**
     * This simple method lets us avoid having to append Reset for every colour we add.
     *
     * @param colour the desired colour for the string, with the Colour enum
     * @param string the string you want to colour
     * @return Coloured string
     */
    protected static String colourString(Colours colour, String string) {
        return colour + string + Colours.RESET;
    }

    /**
     * Populate the CRM with dummy data so lists are not empty at app startup.
     */
    private void populateCRM() {
        Contact contact1 = new Contact(new Lead("Esteban Coestáocupado", "687493822", "esteban@email.com", "BBVA"));
        Contact contact2 = new Contact(new Lead("Federico Trillo", "675392876", "fede@email.com", "Construcciones Trillo S.L."));

        this.crm.addLead(new Lead("Benito Pérez", "636227551", "beni@email.com", "MediaMarkt"));
        this.crm.addLead(new Lead("Coronel Tapioca", "636726671", "tapi@email.com", "Inditex"));
        this.crm.addLead(new Lead("Juan Benigómez", "637538792", "per@email.com", "Keychron"));

        this.crm.addOpportunity(new Opportunity(3, Product.FLATBED, Status.OPEN, contact1));
        this.crm.addOpportunity(new Opportunity(5, Product.HYBRID, Status.CLOSED_WON, contact2));

        this.crm.addContact(contact1);
        this.crm.addContact(contact2);

        this.crm.addAccount(new Account(Industry.MANUFACTURING, 135, "Barcelona", "Spain"));
        this.crm.addAccount(new Account(Industry.ECOMMERCE, 56, "Madrid", "Spain"));
    }
}
