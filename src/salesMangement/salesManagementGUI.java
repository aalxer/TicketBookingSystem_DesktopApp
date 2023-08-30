package salesMangement;

import salesMangement.data.Ticket;
import salesMangement.enume.Cities;
import salesMangement.enume.TicketsEnum;
import salesMangement.exceptions.AmountException;
import salesMangement.exceptions.StatusExceptions;
import salesMangement.exceptions.TicketNotFoundException;
import schedulesManagement.StringException;
import tcpThread.TCPStream;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 *
 * @author alxer
*/
public class salesManagementGUI extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel topPanel;
    private JLabel label1;
    private JPanel sidePanel;
    private JButton startBtn;
    private JButton paymentBtn;
    private JPanel parentPanel;
    private JPanel startContentPanel;
    private JTextField startCity;
    private JTextField disCity;
    private JLabel startCityText;
    private JLabel disCityText;
    private JButton singleTicketBtn;
    private JButton dayTicketBtn;
    private JButton weeklyTicketBtn;
    private JPanel paymentContentPanel;
    private JTextField moneyFeld;
    private JButton insertMoneyBtn;
    private JLabel euroText;
    private JButton cancelBtn;
    private JLabel amountTextInfo;
    private JLabel label2;
    private JLabel ticketCostInfo;
    private JPanel ticketPrintPanel;
    private JLabel ticketPrintInfo;
    private JLabel changeInfoText;
    private JButton backToStartBtn;
    private JScrollPane scrollPane1;
    private JTextArea ticketPrintArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private final TicketAutomatImpl ticketAutomat;
    private double ticketCost = 0;
    private double missingAmount = 0;

    public salesManagementGUI() throws IOException {

        ticketAutomat = new TicketAutomatImpl();

        // TCP connection :
        int port = 2462;
        boolean asServer = false;
        String name = "TCPStream";
        TCPStream tcpStream = new TCPStream(port, asServer, name, ticketAutomat);
        // TCP start :
        tcpStream.start();
        // GUI
        initComponents();
    }

    // ------------------------------------------------------------
    // Events Listener

    private void singleTicketBtn() {

        this.getTicket("EINZEL_TICKET");
    }

    private void dayTicketBtn() {

        this.getTicket("TAGESTICKET");
    }

    private void weeklyTicketBtn() {

        this.getTicket("WOCHEN_TICKET");
    }
    

    private void insertMoneyBtn() {

        try {
            String amountInString = moneyFeld.getText();
            double amountInDouble = getAmountInDouble(amountInString);
            // ausstehender Betrag berechnen
            this.missingAmount = this.missingAmount - amountInDouble;

            double rest = ticketAutomat.zahlen(amountInDouble);
            this.panelSwitch(ticketPrintPanel);

            // Print Ticket
            ticketPrintInfo.setText("Your ticket is ready to take !\n");
            Ticket ticketSold = ticketAutomat.ticketPrint();

            ticketPrintArea.setText("-------------------------------------------------------------\n");
            ticketPrintArea.append(ticketSold.getTicketTyp().toString()+"\n");
            ticketPrintArea.append("von : "+ticketSold.getStartOrt().toString()+" -> nach : "+ticketSold.getZielOrt().toString()+"\n");
            ticketPrintArea.append("preis : "+ticketSold.getPreis()+"€\n");
            ticketPrintArea.append("Verkaufsdatum : "+ticketSold.getDate()+"\n");
            ticketPrintArea.append("-------------------------------------------------------------\n");

            // SidePanel reaction
            startBtn.setBackground(new Color(33, 105, 160));
            paymentBtn.setBackground(new Color(33, 105, 160));

            // Rückgeld zurückgeben
            changeInfoText.setText("your change : "+rest+"€");

        } catch (StatusExceptions ex) {
            JOptionPane.showMessageDialog(new JFrame(),"State error",ex.toString(),JOptionPane.ERROR_MESSAGE);
        } catch (AmountException ignored) {
            amountTextInfo.setText("accepted, "+this.missingAmount+"€ to pay ..");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"amount is not accepted",ex.toString(),JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelBtn() {

        // ticketObj aufräumen
        ticketAutomat.cancel();

        this.panelSwitch(startContentPanel);

        // SidePanel reaction
        paymentBtn.setBackground(new Color(33, 105, 160));
        startBtn.setBackground(new Color(76, 143, 194));
    }

    private void singleTicketBtnMouseEntered() {
        singleTicketBtn.setBackground(new Color(161, 195, 220));
    }

    private void singleTicketBtnMouseExited() {

        singleTicketBtn.setBackground(new Color(76, 143, 194));
    }

    private void dayTicketBtnMouseEntered() {
        dayTicketBtn.setBackground(new Color(161, 195, 220));
    }

    private void dayTicketBtnMouseExited() {

        dayTicketBtn.setBackground(new Color(76, 143, 194));
    }

    private void weeklyTicketBtnMouseEntered() {

        weeklyTicketBtn.setBackground(new Color(161, 195, 220));
    }

    private void weeklyTicketBtnMouseExited() {

        weeklyTicketBtn.setBackground(new Color(76, 143, 194));
    }

    private void insertMoneyBtnMouseEntered() {

        insertMoneyBtn.setBackground(new Color(161, 195, 220));
    }

    private void insertMoneyBtnMouseExited() {

        insertMoneyBtn.setBackground(new Color(76, 143, 194));
    }

    private void cancelBtnMouseEntered() {

        cancelBtn.setBackground(new Color(161, 195, 220));
    }

    private void cancelBtnMouseExited() {

        cancelBtn.setBackground(new Color(76, 143, 194));
    }

    private void backToStartBtnMouseEntered() {

        backToStartBtn.setBackground(new Color(161, 195, 220));
    }

    private void backToStartBtnMouseExited() {
        backToStartBtn.setBackground(new Color(76, 143, 194));
    }

    // ------------------------------------------------------------
    // private methods

    private Cities getCity(String cityInString) throws StringException {
        for (int i = 0 ; i < Cities.values().length ; i++) {
            if (cityInString.equalsIgnoreCase(Cities.values()[i].toString())) {
                return Cities.values()[i];
            }
        }
        throw new StringException();
    }

    private TicketsEnum getTicketTyp(String ticketTyp) throws StringException {
        for (int i = 0 ; i < TicketsEnum.values().length ; i++) {
            if (ticketTyp.equalsIgnoreCase(TicketsEnum.values()[i].toString())) {
                return TicketsEnum.values()[i];
            }
        }
        throw new StringException();
    }

    private double getAmountInDouble(String amount) throws NumberFormatException{
        return Double.parseDouble(amount);
    }

    private void getTicket(String ticketTyp) {

        try {
            Cities city1 = this.getCity(startCity.getText());
            Cities city2 = this.getCity(disCity.getText());
            TicketsEnum ticket = this.getTicketTyp(ticketTyp);

            this.ticketAutomat.startOrt(city1);
            this.ticketAutomat.zielOrt(city2);
            this.ticketCost = ticketAutomat.ticketTyp(ticket);

            // switch zu dem Zahlungspanel
            ticketCostInfo.setText(this.ticketCost + "€ are to be paid :");
            this.panelSwitch(paymentContentPanel);

            // SidePanel reaction
            startBtn.setBackground(new Color(33, 105, 160));
            paymentBtn.setBackground(new Color(76, 143, 194));

            this.missingAmount = this.ticketCost;

        } catch (StatusExceptions ex) {
            JOptionPane.showMessageDialog(new JFrame(),"State error",ex.toString(),JOptionPane.ERROR_MESSAGE);
        } catch (StringException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"check your input data",ex.toString(),JOptionPane.ERROR_MESSAGE);
        } catch (TicketNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"Ticket not found",ex.toString(),JOptionPane.ERROR_MESSAGE);
        }
    }

    private void panelSwitch(JPanel panel) {

        parentPanel.removeAll();
        parentPanel.add(panel);
        parentPanel.repaint();
        parentPanel.revalidate();
    }

    // ------------------------------------------------------------
    // create GUI

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        topPanel = new JPanel();
        label1 = new JLabel();
        sidePanel = new JPanel();
        startBtn = new JButton();
        paymentBtn = new JButton();
        parentPanel = new JPanel();
        startContentPanel = new JPanel();
        startCity = new JTextField();
        disCity = new JTextField();
        startCityText = new JLabel();
        disCityText = new JLabel();
        singleTicketBtn = new JButton();
        dayTicketBtn = new JButton();
        weeklyTicketBtn = new JButton();
        paymentContentPanel = new JPanel();
        moneyFeld = new JTextField();
        insertMoneyBtn = new JButton();
        euroText = new JLabel();
        cancelBtn = new JButton();
        amountTextInfo = new JLabel();
        label2 = new JLabel();
        ticketCostInfo = new JLabel();
        ticketPrintPanel = new JPanel();
        ticketPrintInfo = new JLabel();
        changeInfoText = new JLabel();
        backToStartBtn = new JButton();
        scrollPane1 = new JScrollPane();
        ticketPrintArea = new JTextArea();

        //======== this ========
        setPreferredSize(new Dimension(830, 590));

        //======== topPanel ========
        {
            topPanel.setBackground(new Color(18, 62, 93));
            topPanel.setPreferredSize(new Dimension(600, 100));
            topPanel.setMinimumSize(new Dimension(0, 0));
            topPanel.setMaximumSize(new Dimension(3274, 3247));

            //---- label1 ----
            label1.setText("Ticket vending machine");
            label1.setForeground(Color.white);
            label1.setFont(new Font("Century Schoolbook", Font.BOLD, 30));
            label1.setAlignmentY(2.5F);

            GroupLayout topPanelLayout = new GroupLayout(topPanel);
            topPanel.setLayout(topPanelLayout);
            topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup()
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup()
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(13, Short.MAX_VALUE))
            );
        }

        //======== sidePanel ========
        {
            sidePanel.setBackground(new Color(33, 105, 160));
            sidePanel.setPreferredSize(new Dimension(600, 110));
            sidePanel.setMinimumSize(new Dimension(135, 216));

            //---- startBtn ----
            startBtn.setText("Start");
            startBtn.setForeground(Color.white);
            startBtn.setFont(new Font("Century Gothic", Font.PLAIN, 26));
            startBtn.setBackground(new Color(76, 143, 194));
            startBtn.setBorder(null);
            startBtn.setFocusPainted(false);
            startBtn.setHorizontalAlignment(SwingConstants.LEFT);
            startBtn.setIcon(new ImageIcon("D:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-location-pin-thin-32.png"));

            //---- paymentBtn ----
            paymentBtn.setText("Payment");
            paymentBtn.setForeground(Color.white);
            paymentBtn.setFont(new Font("Century Gothic", Font.PLAIN, 26));
            paymentBtn.setBackground(new Color(33, 105, 160));
            paymentBtn.setFocusPainted(false);
            paymentBtn.setBorder(null);
            paymentBtn.setHorizontalAlignment(SwingConstants.LEFT);
            paymentBtn.setIcon(new ImageIcon("D:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-credit-card-thin-32.png"));
            paymentBtn.setIconTextGap(10);

            GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
            sidePanel.setLayout(sidePanelLayout);
            sidePanelLayout.setHorizontalGroup(
                sidePanelLayout.createParallelGroup()
                    .addComponent(startBtn, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(paymentBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            );
            sidePanelLayout.setVerticalGroup(
                sidePanelLayout.createParallelGroup()
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(startBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymentBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        //======== parentPanel ========
        {
            parentPanel.setLayout(new CardLayout());

            //======== startContentPanel ========
            {
                startContentPanel.setFont(new Font("Bellerose", Font.PLAIN, 12));

                //---- startCity ----
                startCity.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                startCity.setBackground(Color.white);
                startCity.setForeground(Color.gray);

                //---- disCity ----
                disCity.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                disCity.setBackground(Color.white);
                disCity.setForeground(Color.gray);

                //---- startCityText ----
                startCityText.setText("Start city :");
                startCityText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                startCityText.setForeground(Color.gray);

                //---- disCityText ----
                disCityText.setText("Destination city :");
                disCityText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                disCityText.setForeground(Color.gray);

                //---- singleTicketBtn ----
                singleTicketBtn.setText("Single ticket");
                singleTicketBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                singleTicketBtn.setBackground(new Color(76, 143, 194));
                singleTicketBtn.setForeground(Color.white);
                singleTicketBtn.setBorder(null);
                singleTicketBtn.setFocusPainted(false);
                singleTicketBtn.addActionListener(e -> singleTicketBtn());
                singleTicketBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        singleTicketBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        singleTicketBtnMouseExited();
                    }
                });

                //---- dayTicketBtn ----
                dayTicketBtn.setText("Day ticket ");
                dayTicketBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                dayTicketBtn.setBackground(new Color(76, 143, 194));
                dayTicketBtn.setForeground(Color.white);
                dayTicketBtn.setFocusPainted(false);
                dayTicketBtn.setBorder(null);
                dayTicketBtn.addActionListener(e -> dayTicketBtn());
                dayTicketBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        dayTicketBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        dayTicketBtnMouseExited();
                    }
                });

                //---- weeklyTicketBtn ----
                weeklyTicketBtn.setText("Weekly ticket ");
                weeklyTicketBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                weeklyTicketBtn.setBackground(new Color(76, 143, 194));
                weeklyTicketBtn.setForeground(Color.white);
                weeklyTicketBtn.setFocusPainted(false);
                weeklyTicketBtn.setBorder(null);
                weeklyTicketBtn.addActionListener(e -> weeklyTicketBtn());
                weeklyTicketBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        weeklyTicketBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        weeklyTicketBtnMouseExited();
                    }
                });

                GroupLayout startContentPanelLayout = new GroupLayout(startContentPanel);
                startContentPanel.setLayout(startContentPanelLayout);
                startContentPanelLayout.setHorizontalGroup(
                    startContentPanelLayout.createParallelGroup()
                        .addGroup(startContentPanelLayout.createSequentialGroup()
                            .addGroup(startContentPanelLayout.createParallelGroup()
                                .addGroup(startContentPanelLayout.createSequentialGroup()
                                    .addGap(56, 56, 56)
                                    .addGroup(startContentPanelLayout.createParallelGroup()
                                        .addComponent(startCityText, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startCity, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE))
                                    .addGap(57, 57, 57)
                                    .addGroup(startContentPanelLayout.createParallelGroup()
                                        .addComponent(disCityText, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(disCity, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(startContentPanelLayout.createSequentialGroup()
                                    .addGap(144, 144, 144)
                                    .addGroup(startContentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(dayTicketBtn, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(singleTicketBtn, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(weeklyTicketBtn, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(136, Short.MAX_VALUE))
                );
                startContentPanelLayout.setVerticalGroup(
                    startContentPanelLayout.createParallelGroup()
                        .addGroup(startContentPanelLayout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addGroup(startContentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(startContentPanelLayout.createSequentialGroup()
                                    .addComponent(disCityText)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(disCity, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                                .addGroup(startContentPanelLayout.createSequentialGroup()
                                    .addComponent(startCityText)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(startCity, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
                            .addGap(67, 67, 67)
                            .addComponent(singleTicketBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(dayTicketBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(weeklyTicketBtn, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(164, Short.MAX_VALUE))
                );
            }
            parentPanel.add(startContentPanel, "card1");

            //======== paymentContentPanel ========
            {
                paymentContentPanel.setMinimumSize(new Dimension(363, 228));

                //---- moneyFeld ----
                moneyFeld.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                moneyFeld.setBackground(Color.white);
                moneyFeld.setForeground(Color.gray);

                //---- insertMoneyBtn ----
                insertMoneyBtn.setText("INSERT");
                insertMoneyBtn.setFont(new Font("Century Gothic", Font.PLAIN, 22));
                insertMoneyBtn.setBackground(new Color(76, 143, 194));
                insertMoneyBtn.setForeground(Color.white);
                insertMoneyBtn.setFocusPainted(false);
                insertMoneyBtn.setBorder(null);
                insertMoneyBtn.setIcon(new ImageIcon("D:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-angel-down-thin-32.png"));
                insertMoneyBtn.addActionListener(e -> insertMoneyBtn());
                insertMoneyBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        insertMoneyBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        insertMoneyBtnMouseExited();
                    }
                });

                //---- euroText ----
                euroText.setText("\u20ac");
                euroText.setFont(new Font("Arial", Font.PLAIN, 24));
                euroText.setForeground(Color.gray);

                //---- cancelBtn ----
                cancelBtn.setText("CANCEL");
                cancelBtn.setFont(new Font("Century Gothic", Font.PLAIN, 22));
                cancelBtn.setBackground(new Color(76, 143, 194));
                cancelBtn.setForeground(Color.white);
                cancelBtn.setFocusPainted(false);
                cancelBtn.setBorder(null);
                cancelBtn.setIcon(new ImageIcon("D:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-x-mark-thin-32.png"));
                cancelBtn.addActionListener(e -> cancelBtn());
                cancelBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        cancelBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        cancelBtnMouseExited();
                    }
                });

                //---- amountTextInfo ----
                amountTextInfo.setForeground(new Color(29, 219, 93));
                amountTextInfo.setFont(new Font("Century Gothic", Font.PLAIN, 18));

                //---- label2 ----
                label2.setIcon(new ImageIcon("D:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-banknote-20-64 (1).png"));

                //---- ticketCostInfo ----
                ticketCostInfo.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                ticketCostInfo.setForeground(Color.gray);

                GroupLayout paymentContentPanelLayout = new GroupLayout(paymentContentPanel);
                paymentContentPanel.setLayout(paymentContentPanelLayout);
                paymentContentPanelLayout.setHorizontalGroup(
                    paymentContentPanelLayout.createParallelGroup()
                        .addGroup(paymentContentPanelLayout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(paymentContentPanelLayout.createParallelGroup()
                                .addGroup(paymentContentPanelLayout.createSequentialGroup()
                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(moneyFeld, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(euroText, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addGap(277, 362, Short.MAX_VALUE))
                                .addGroup(paymentContentPanelLayout.createSequentialGroup()
                                    .addComponent(amountTextInfo, GroupLayout.PREFERRED_SIZE, 419, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 139, Short.MAX_VALUE))
                                .addComponent(ticketCostInfo, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)))
                        .addGroup(paymentContentPanelLayout.createSequentialGroup()
                            .addGap(137, 137, 137)
                            .addComponent(insertMoneyBtn, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
                            .addGap(17, 17, 17)
                            .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 162, Short.MAX_VALUE))
                );
                paymentContentPanelLayout.setVerticalGroup(
                    paymentContentPanelLayout.createParallelGroup()
                        .addGroup(paymentContentPanelLayout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addComponent(ticketCostInfo, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(paymentContentPanelLayout.createParallelGroup()
                                .addGroup(paymentContentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(moneyFeld, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(euroText, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(amountTextInfo, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addGroup(paymentContentPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(insertMoneyBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(244, Short.MAX_VALUE))
                );
            }
            parentPanel.add(paymentContentPanel, "card2");

            //======== ticketPrintPanel ========
            {

                //---- ticketPrintInfo ----
                ticketPrintInfo.setFont(new Font("Century Gothic", Font.PLAIN, 20));
                ticketPrintInfo.setForeground(new Color(51, 99, 169));
                ticketPrintInfo.setIcon(new ImageIcon("D:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\check2.png"));
                ticketPrintInfo.setHorizontalAlignment(SwingConstants.CENTER);

                //---- changeInfoText ----
                changeInfoText.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                changeInfoText.setForeground(new Color(51, 99, 169));
                changeInfoText.setHorizontalAlignment(SwingConstants.CENTER);

                //---- backToStartBtn ----
                backToStartBtn.setText("Back to start page");
                backToStartBtn.setFont(new Font("Century Gothic", Font.PLAIN, 20));
                backToStartBtn.setForeground(Color.white);
                backToStartBtn.setBackground(new Color(76, 143, 194));
                backToStartBtn.setFocusPainted(false);
                backToStartBtn.setBorder(null);
                backToStartBtn.addActionListener(e -> cancelBtn());
                backToStartBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        backToStartBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        backToStartBtnMouseExited();
                    }
                });

                //======== scrollPane1 ========
                {

                    //---- ticketPrintArea ----
                    ticketPrintArea.setForeground(Color.gray);
                    ticketPrintArea.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                    scrollPane1.setViewportView(ticketPrintArea);
                }

                GroupLayout ticketPrintPanelLayout = new GroupLayout(ticketPrintPanel);
                ticketPrintPanel.setLayout(ticketPrintPanelLayout);
                ticketPrintPanelLayout.setHorizontalGroup(
                    ticketPrintPanelLayout.createParallelGroup()
                        .addGroup(ticketPrintPanelLayout.createSequentialGroup()
                            .addGroup(ticketPrintPanelLayout.createParallelGroup()
                                .addGroup(ticketPrintPanelLayout.createSequentialGroup()
                                    .addGap(116, 116, 116)
                                    .addGroup(ticketPrintPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane1, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ticketPrintInfo, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(changeInfoText, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(ticketPrintPanelLayout.createSequentialGroup()
                                    .addGap(167, 167, 167)
                                    .addComponent(backToStartBtn, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(142, Short.MAX_VALUE))
                );
                ticketPrintPanelLayout.setVerticalGroup(
                    ticketPrintPanelLayout.createParallelGroup()
                        .addGroup(ticketPrintPanelLayout.createSequentialGroup()
                            .addGap(66, 66, 66)
                            .addComponent(ticketPrintInfo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                            .addGap(29, 29, 29)
                            .addComponent(changeInfoText, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(backToStartBtn, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(143, Short.MAX_VALUE))
                );
            }
            parentPanel.add(ticketPrintPanel, "card3");
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(sidePanel, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(parentPanel, GroupLayout.PREFERRED_SIZE, 646, GroupLayout.PREFERRED_SIZE))
                .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addGroup(layout.createParallelGroup()
                        .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                        .addComponent(parentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // ------------------------------------------------------------
    // Start

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Ticket vending machine");
        frame.setContentPane(new salesManagementGUI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}