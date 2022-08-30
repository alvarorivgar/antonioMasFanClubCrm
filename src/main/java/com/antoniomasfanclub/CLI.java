package com.antoniomasfanclub;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

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

    public void startCRM() {
        printer.println(Colours.BACKGROUND_YELLOW + "@@@@@@@@@@@@ Welcome to the " + Colours.RED + "🍆Antonio Mas👼🏻 Fan Club CRM®️" + Colours.BLACK + "! @@@@@@@@@@@@" + Colours.RESET);
        boolean run = true;

        do {
            seeCRMOptions();
            String userInput = scanner.nextLine().trim().toLowerCase();
            switch (userInput) {
                case "new lead" -> createNewLead();
                case "list leads" -> printList(this.crm.getLeads());
                case "quit" -> run = false;
                default ->
                        printer.println("Sorry, I do not understand '" + colourString(Colours.YELLOW, userInput) + "'. Could you try again?");
            }
        } while (run);
        printer.println("Quitting the CRM. " + colourString(Colours.YELLOW, "Have a great day!"));
    }

    public void createNewLead() {
        Lead lead = new Lead();
        upgradeStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "👤 name") + ":", lead::setName);
        upgradeStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "🏢 company name") + ":", lead::setCompanyName);
        upgradeStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "☎️ phone number") + ":", lead::setPhoneNumber);
        upgradeStringKey("Please introduce this lead's " + colourString(Colours.CYAN, "✉️ email") + ":", lead::setEmail);

        this.crm.addLead(lead);
        printer.println(colourString(Colours.GREEN, "Success!") + " Lead with ID " + colourString(Colours.CYAN, lead.getId() + "") + " was added to the leads list.");
    }

    public <T> void printList(Map<Integer, T> list) {
        for (int key : list.keySet()) {
            printer.println(list.get(key));
        }
    }

    public void seeCRMOptions() {
        printer.println("- To create a new lead, type '" + colourString(Colours.GREEN, Command.NEW_LEAD.toString()) + "' ");
        printer.println("- To see all current leads, type '" + colourString(Colours.GREEN, Command.LIST_LEADS.toString()) + "' ");
        printer.println("- To quit the CRM, type type '" + colourString(Colours.RED, Command.QUIT.toString()) + "' ");
    }

    private void upgradeStringKey(String message, Consumer<String> updateMethod) {
        updateKey(message, () -> updateMethod.accept(scanner.nextLine()));
    }

    private void updateCharacterIntegerKey(String message, Consumer<Integer> updateMethod) {
        updateKey(message, () -> {
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

    private void updateKey(String message, Runnable updateMethod) {
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

    private String colourString(Colours colour, String string) {
        return colour + string + Colours.RESET;
    }

    private void populateCRM() {
        this.crm.addLead(new Lead("Benito Pérez", "636227551", "beni@email.com", "MediaMarkt"));
        this.crm.addLead(new Lead("Coronel Tapioca", "636726671", "tapi@email.com", "Inditex"));
        this.crm.addLead(new Lead("Juan Benigómez", "637538792", "per@email.com", "Keychron"));
    }
}
