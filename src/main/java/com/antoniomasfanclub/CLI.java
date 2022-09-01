package com.antoniomasfanclub;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
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

    private void printCRMOptions() {
        printer.println();
        printer.println("- To create a new lead, type '" + colourString(Colours.GREEN, Command.NEW_LEAD.toString()) + "' ");
        printer.println("- To see all current leads, contacts, accounts or opportunities, type '" + colourString(Colours.GREEN, Command.LIST_LEADS.toString()) + "' or the equivalent");
        printer.println("- To convert a lead into an opportunity type '" + colourString(Colours.GREEN, Command.CONVERT.toString()) + "' followed by the " + colourString(Colours.GREEN, "lead id"));
        printer.println("- To quit the CRM, type type '" + colourString(Colours.RED, Command.QUIT.toString()) + "' ");
    }

    public void startCRM() {
        printer.println(Colours.BACKGROUND_YELLOW + "@@@@@@@@@@@@ Welcome to the " + Colours.RED + "🍆Antonio Mas👼🏻 Fan Club CRM®️" + Colours.BLACK + "! @@@@@@@@@@@@" + Colours.RESET);
        boolean run = true;

        do {
            printCRMOptions();
            String[] userInput = scanner.nextLine().trim().toLowerCase().split(" ");
            switch (userInput[0]) {
                case "new":
                    if (userInput[1].equals("lead")) {
                        createNewLead();
                        break;
                    }
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
                case "convert":
                    convertLead(userInput[1]);
                    break;
                case "quit":
                    run = false;
                    break;
                default:
                    printer.println("Sorry, I do not understand '" + colourString(Colours.YELLOW, String.join(" ", userInput)) + "'. Could you try again?");
            }
        } while (run);
        printer.println("Quitting the CRM. " + colourString(Colours.YELLOW, "Have a great day!"));
    }

    private void createNewLead() {
        Lead lead = new Lead();
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "👤 name") + ":", lead::setName);
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "🏢 company name") + ":", lead::setCompanyName);
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "☎️ phone number") + ":", lead::setPhoneNumber);
        updateStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "✉️ email") + ":", lead::setEmail);

        this.crm.addLead(lead);
        printer.println(colourString(Colours.GREEN, "Success!") + " Lead with ID " + colourString(Colours.CYAN, lead.getId() + "") + " was added to the leads list.");
    }

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
            printer.println(colourString(Colours.RED, "Error") + " - " + e + "\n");
        }
    }

    private Opportunity createOpportunity(Contact contact) {
        Opportunity opportunity = new Opportunity();
        opportunity.setContact(contact);
        printer.println("Creating a new " + colourString(Colours.GREEN, "opportunity"));
        updateIntegerKey("Please input this opportunity's quantity", opportunity::setQuantity);
        updateEnumKey(Product.BOX, opportunity::setProduct, opportunity::getProduct);
        updateEnumKey(Status.OPEN, opportunity::setStatus, opportunity::getStatus);
        return opportunity;
    }

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

    private <T> void printList(Map<Integer, T> list) {
        if (list.keySet().size() == 0 )
            printer.println("There are no items in this list.");
        for (int key : list.keySet()) {
            printer.println(list.get(key));
        }
    }

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

    private void updateStringKey(String message, Consumer<String> updateMethod) {
        updateGenericKey(message, () -> updateMethod.accept(scanner.nextLine()));
    }

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
            } else printer.println("Only integers are allowed as options, please try again.");
            if (nextInt > 0 && nextInt <= enumValues.length) {
                setter.accept(enumValues[nextInt - 1]);
            }
        } while (getter.get() == null);
    }

    protected static String colourString(Colours colour, String string) {
        return colour + string + Colours.RESET;
    }

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
