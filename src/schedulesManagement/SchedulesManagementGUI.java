package schedulesManagement;

import java.awt.event.*;
import salesMangement.enume.Cities;
import salesMangement.enume.TicketsEnum;
import schedulesManagement.data.Schedules;
import schedulesManagement.data.TicketsPreis;
import tcpThread.TCPStream;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author alxer
 */
public class SchedulesManagementGUI extends JPanel {

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel topPanel;
    private JLabel label1;
    private JPanel sidePanel;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton printAllBtn;
    private JButton homepageBtn;
    private JButton helpBtn;
    private JPanel parentPanel;
    private JPanel startPanel;
    private JLabel startPanelTitel;
    private JLabel startPanelIcon;
    private JLabel label2;
    private JPanel addSchedulePanel;
    private JTextField startCityFeld;
    private JTextField desCityFeld;
    private JTextField ticketTyp1Cost;
    private JLabel startCityText;
    private JLabel disCityText;
    private JLabel ticketCostText;
    private JTextField ticketTyp2Cost;
    private JTextField ticketTyp3Cost;
    private JComboBox<String> ticketTypComboBox1;
    private JComboBox<String> ticketTypComboBox2;
    private JComboBox<String> ticketTypComboBox3;
    private JLabel ticketTypText;
    private JLabel euroText;
    private JLabel euroText2;
    private JLabel euroText3;
    private JButton addToListBtn2;
    private JButton cancelBtn2;
    private JLabel addConfirmFeld;
    private JPanel removePanel;
    private JLabel removePanelText;
    private JTextField indexInputFeld;
    private JButton cancelBtn;
    private JButton deleteScheduleBtn;
    private JLabel removeConfirmFeld;
    private JPanel helpPanel;
    private JButton reportBtn;
    private JButton supportCallBtn;
    private JButton sendEmailBtn;
    private JLabel sendEmailIcon;
    private JLabel supportCallIcon;
    private JLabel reportIcon;
    private JPanel sideBar;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private final SchedulesManagementImpl schedulesManagement;

    public SchedulesManagementGUI() throws IOException {

        this.schedulesManagement = new SchedulesManagementImpl();

        // TCP connection :
        int port = 2462;
        boolean asServer = true;
        String name = "TCPStream";
        TCPStream tcpStream = new TCPStream(port,asServer,name,schedulesManagement);
        // TCP start :
        tcpStream.start();
        // GUI
        initComponents();
    }

    // ------------------------------------------------------------
    // Events Listener

    private void addBtn() {

        this.panelSwitch(addSchedulePanel);
        addBtn.setBackground(new Color(76, 143, 194));
    }

    private void addBtnMouseEntered() {


        addBtn.setBackground(new Color(76, 143, 194));
    }

    private void addBtnMouseExited() {


        addBtn.setBackground(new Color(33, 105, 160));
    }

    private void removeBtnMouseEntered() {

        removeBtn.setBackground(new Color(76, 143, 194));
    }

    private void removeBtnMouseExited() {

        removeBtn.setBackground(new Color(33, 105, 160));
    }

    private void printAllBtnMouseEntered() {

        printAllBtn.setBackground(new Color(76, 143, 194));
    }

    private void printAllBtnMouseExited() {

        printAllBtn.setBackground(new Color(33, 105, 160));
    }

    private void homepageBtnMouseEntered() {

        homepageBtn.setBackground(new Color(76, 143, 194));
    }

    private void homepageBtnMouseExited() {

        homepageBtn.setBackground(new Color(33, 105, 160));
    }

    private void homepageBtn() {

        this.panelSwitch(startPanel);
        homepageBtn.setBackground(new Color(76, 143, 194));
    }

    private void addToListBtn2MouseEntered() {


        addToListBtn2.setForeground(new Color(192,192,192));
    }

    private void addToListBtn2MouseExited() {

        addToListBtn2.setForeground(Color.gray);
    }

    private void cancelBtn2MouseEntered() {

        cancelBtn2.setForeground(new Color(192,192,192));
    }

    private void cancelBtn2MouseExited() {

        cancelBtn2.setForeground(Color.gray);
    }

    private void cancelBtnMouseEntered() {

        cancelBtn.setForeground(new Color(192,192,192));
    }

    private void cancelBtnMouseExited() {

        cancelBtn.setForeground(Color.gray);
    }

    private void deleteScheduleBtnMouseEntered() {
        deleteScheduleBtn.setForeground(new Color(192,192,192));
    }

    private void deleteScheduleBtnMouseExited() {

        deleteScheduleBtn.setForeground(Color.gray);
    }

    private void removeBtn() {
        this.panelSwitch(removePanel);
        removeBtn.setBackground(new Color(76, 143, 194));
    }

    private void helpBtnMouseEntered() {

        helpBtn.setBackground(new Color(76, 143, 194));
    }

    private void helpBtnMouseExited() {

        helpBtn.setBackground(new Color(33, 105, 160));
    }

    private void helpBtn() {

        this.panelSwitch(helpPanel);
        helpBtn.setBackground(new Color(76, 143, 194));
    }

    private void reportBtnMouseEntered() {


        reportBtn.setForeground(new Color(192,192,192));
    }

    private void reportBtnMouseExited() {

        reportBtn.setForeground(Color.gray);
    }

    private void supportCallBtnMouseEntered() {

        supportCallBtn.setForeground(new Color(192,192,192));
    }

    private void supportCallBtnMouseExited() {

        supportCallBtn.setForeground(Color.gray);
    }

    private void sendEmailBtnMouseEntered() {

        sendEmailBtn.setForeground(new Color(192,192,192));
    }

    private void sendEmailBtnMouseExited() {

        sendEmailBtn.setForeground(Color.gray);
    }

    private void addToListBtn2() {

        try {
            // Inputs einlesen :
            String startCityInString = startCityFeld.getText();
            Cities startCity = this.getCity(startCityInString);
            String desCityInString = desCityFeld.getText();
            Cities desCity = this.getCity(desCityInString);

            ArrayList<TicketsPreis> tickets = new ArrayList<>();

            String ticketTyp1InString = ticketTypComboBox1.getSelectedItem().toString();
            TicketsEnum ticketTyp1 = this.getTicketTyp(ticketTyp1InString);
            double ticket1Cost = this.getAmountInDouble(ticketTyp1Cost.getText());
            tickets.add(new TicketsPreis(ticketTyp1,ticket1Cost));

            String ticketTyp2InString = ticketTypComboBox2.getSelectedItem().toString();
            TicketsEnum ticketTyp2 = this.getTicketTyp(ticketTyp2InString);
            double ticket2Cost = this.getAmountInDouble(ticketTyp2Cost.getText());
            tickets.add(new TicketsPreis(ticketTyp2,ticket2Cost));

            String ticketTyp3InString = ticketTypComboBox3.getSelectedItem().toString();
            TicketsEnum ticketTyp3 = this.getTicketTyp(ticketTyp3InString);
            double ticket3Cost = this.getAmountInDouble(ticketTyp3Cost.getText());
            tickets.add(new TicketsPreis(ticketTyp3,ticket3Cost));

            // versuche hinzufügen :
            this.schedulesManagement.add(new Schedules(startCity,desCity,tickets));

            // bestätigungsinfo anzeigen:
            addConfirmFeld.setText("schedule has been successfully added");
            addConfirmFeld.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\icons8-h\u00e4kchen-48.png"));

        } catch (TicketExistsException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"the ticket already exists",ex.toString(),JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (StringException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"check your input data",ex.toString(),JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"amount is not accepted",ex.toString(),JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteScheduleBtn() {
        try {
            int index = Integer.parseInt(indexInputFeld.getText());
            this.schedulesManagement.delete(index);
            // bestätigungsinfo anzeigen:
            removeConfirmFeld.setText("schedule has been successfully deleted");
            removeConfirmFeld.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\icons8-h\u00e4kchen-48.png"));

        } catch (MemoryEmptyException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"no schedules are saved ",ex.toString(),JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(new JFrame(),"the index is out of bound the schedules",ex.toString(),JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
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
        addBtn = new JButton();
        removeBtn = new JButton();
        printAllBtn = new JButton();
        homepageBtn = new JButton();
        helpBtn = new JButton();
        parentPanel = new JPanel();
        startPanel = new JPanel();
        startPanelTitel = new JLabel();
        startPanelIcon = new JLabel();
        label2 = new JLabel();
        addSchedulePanel = new JPanel();
        startCityFeld = new JTextField();
        desCityFeld = new JTextField();
        ticketTyp1Cost = new JTextField();
        startCityText = new JLabel();
        disCityText = new JLabel();
        ticketCostText = new JLabel();
        ticketTyp2Cost = new JTextField();
        ticketTyp3Cost = new JTextField();
        ticketTypComboBox1 = new JComboBox<>();
        ticketTypComboBox2 = new JComboBox<>();
        ticketTypComboBox3 = new JComboBox<>();
        ticketTypText = new JLabel();
        euroText = new JLabel();
        euroText2 = new JLabel();
        euroText3 = new JLabel();
        addToListBtn2 = new JButton();
        cancelBtn2 = new JButton();
        addConfirmFeld = new JLabel();
        removePanel = new JPanel();
        removePanelText = new JLabel();
        indexInputFeld = new JTextField();
        cancelBtn = new JButton();
        deleteScheduleBtn = new JButton();
        removeConfirmFeld = new JLabel();
        helpPanel = new JPanel();
        reportBtn = new JButton();
        supportCallBtn = new JButton();
        sendEmailBtn = new JButton();
        sendEmailIcon = new JLabel();
        supportCallIcon = new JLabel();
        reportIcon = new JLabel();
        sideBar = new JPanel();

        //======== this ========

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

            //---- addBtn ----
            addBtn.setText("Add new schedule");
            addBtn.setForeground(Color.white);
            addBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
            addBtn.setBackground(new Color(33, 105, 160));
            addBtn.setBorder(null);
            addBtn.setFocusPainted(false);
            addBtn.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-plus-4-32.png"));
            addBtn.addActionListener(e -> addBtn());
            addBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    addBtnMouseEntered();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    addBtnMouseExited();
                }
            });

            //---- removeBtn ----
            removeBtn.setText("Remove schedule");
            removeBtn.setForeground(Color.white);
            removeBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
            removeBtn.setBackground(new Color(33, 105, 160));
            removeBtn.setFocusPainted(false);
            removeBtn.setBorder(null);
            removeBtn.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-minus-4-32.png"));
            removeBtn.setIconTextGap(10);
            removeBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    removeBtnMouseEntered();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    removeBtnMouseExited();
                }
            });
            removeBtn.addActionListener(e -> removeBtn());

            //---- printAllBtn ----
            printAllBtn.setText("print all schedules");
            printAllBtn.setForeground(Color.white);
            printAllBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
            printAllBtn.setBackground(new Color(33, 105, 160));
            printAllBtn.setFocusPainted(false);
            printAllBtn.setBorder(null);
            printAllBtn.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-printer-1-32.png"));
            printAllBtn.setIconTextGap(10);
            printAllBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    printAllBtnMouseEntered();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    printAllBtnMouseExited();
                }
            });

            //---- homepageBtn ----
            homepageBtn.setText("Homepage");
            homepageBtn.setForeground(Color.white);
            homepageBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
            homepageBtn.setBackground(new Color(33, 105, 160));
            homepageBtn.setBorder(null);
            homepageBtn.setFocusPainted(false);
            homepageBtn.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-home-1-32.png"));
            homepageBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    homepageBtnMouseEntered();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    homepageBtnMouseExited();
                }
            });
            homepageBtn.addActionListener(e -> homepageBtn());

            //---- helpBtn ----
            helpBtn.setText("Help");
            helpBtn.setForeground(Color.white);
            helpBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
            helpBtn.setBackground(new Color(33, 105, 160));
            helpBtn.setFocusPainted(false);
            helpBtn.setBorder(null);
            helpBtn.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-help-6-32.png"));
            helpBtn.setIconTextGap(10);
            helpBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    helpBtnMouseEntered();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    helpBtnMouseExited();
                }
            });
            helpBtn.addActionListener(e -> helpBtn());

            GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
            sidePanel.setLayout(sidePanelLayout);
            sidePanelLayout.setHorizontalGroup(
                sidePanelLayout.createParallelGroup()
                    .addComponent(helpBtn, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(homepageBtn, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(addBtn, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(removeBtn, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(printAllBtn, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
            );
            sidePanelLayout.setVerticalGroup(
                sidePanelLayout.createParallelGroup()
                    .addGroup(sidePanelLayout.createSequentialGroup()
                        .addComponent(homepageBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printAllBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                        .addComponent(helpBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
            );
        }

        //======== parentPanel ========
        {
            parentPanel.setLayout(new CardLayout());

            //======== startPanel ========
            {

                //---- startPanelTitel ----
                startPanelTitel.setText("Your Control Panel");
                startPanelTitel.setForeground(Color.gray);
                startPanelTitel.setFont(new Font("Century Gothic", Font.BOLD, 30));
                startPanelTitel.setHorizontalAlignment(SwingConstants.CENTER);
                startPanelTitel.setVerticalAlignment(SwingConstants.TOP);

                //---- startPanelIcon ----
                startPanelIcon.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\iconmonstr-volume-control-9-64.png"));
                startPanelIcon.setHorizontalAlignment(SwingConstants.CENTER);
                startPanelIcon.setVerticalAlignment(SwingConstants.BOTTOM);

                //---- label2 ----
                label2.setText("select an option from the side panel .. ");
                label2.setForeground(Color.gray);
                label2.setFont(new Font("Century Gothic", Font.PLAIN, 22));
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                label2.setVerticalAlignment(SwingConstants.TOP);

                GroupLayout startPanelLayout = new GroupLayout(startPanel);
                startPanel.setLayout(startPanelLayout);
                startPanelLayout.setHorizontalGroup(
                    startPanelLayout.createParallelGroup()
                        .addComponent(startPanelIcon, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                        .addComponent(startPanelTitel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                );
                startPanelLayout.setVerticalGroup(
                    startPanelLayout.createParallelGroup()
                        .addGroup(startPanelLayout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(startPanelIcon, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(startPanelTitel, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(237, Short.MAX_VALUE))
                );
            }
            parentPanel.add(startPanel, "card1");

            //======== addSchedulePanel ========
            {
                addSchedulePanel.setBackground(new Color(240, 240, 240));

                //---- startCityFeld ----
                startCityFeld.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                startCityFeld.setBackground(Color.white);
                startCityFeld.setForeground(Color.gray);

                //---- desCityFeld ----
                desCityFeld.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                desCityFeld.setBackground(Color.white);
                desCityFeld.setForeground(Color.gray);

                //---- ticketTyp1Cost ----
                ticketTyp1Cost.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                ticketTyp1Cost.setBackground(Color.white);
                ticketTyp1Cost.setForeground(Color.gray);

                //---- startCityText ----
                startCityText.setText("Start city :");
                startCityText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                startCityText.setForeground(Color.gray);

                //---- disCityText ----
                disCityText.setText("Destination city :");
                disCityText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                disCityText.setForeground(Color.gray);

                //---- ticketCostText ----
                ticketCostText.setText("Tickets cost :");
                ticketCostText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                ticketCostText.setForeground(Color.gray);

                //---- ticketTyp2Cost ----
                ticketTyp2Cost.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                ticketTyp2Cost.setBackground(Color.white);
                ticketTyp2Cost.setForeground(Color.gray);

                //---- ticketTyp3Cost ----
                ticketTyp3Cost.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                ticketTyp3Cost.setBackground(Color.white);
                ticketTyp3Cost.setForeground(Color.gray);

                //---- ticketTypComboBox1 ----
                ticketTypComboBox1.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                ticketTypComboBox1.setForeground(Color.gray);
                ticketTypComboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                    "EINZEL_TICKET",
                    "WOCHEN_TICKET",
                    "TAGESTICKET"
                }));
                ticketTypComboBox1.setSelectedIndex(-1);

                //---- ticketTypComboBox2 ----
                ticketTypComboBox2.setForeground(Color.gray);
                ticketTypComboBox2.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                ticketTypComboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                    "EINZEL_TICKET",
                    "WOCHEN_TICKET",
                    "TAGESTICKET"
                }));
                ticketTypComboBox2.setSelectedIndex(-1);

                //---- ticketTypComboBox3 ----
                ticketTypComboBox3.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                ticketTypComboBox3.setForeground(Color.gray);
                ticketTypComboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
                    "EINZEL_TICKET",
                    "WOCHEN_TICKET",
                    "TAGESTICKET"
                }));
                ticketTypComboBox3.setSelectedIndex(-1);

                //---- ticketTypText ----
                ticketTypText.setText("Tickets typ :");
                ticketTypText.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                ticketTypText.setForeground(Color.gray);

                //---- euroText ----
                euroText.setText("\u20ac");
                euroText.setFont(new Font("Arial", Font.PLAIN, 24));
                euroText.setForeground(Color.gray);

                //---- euroText2 ----
                euroText2.setText("\u20ac");
                euroText2.setFont(new Font("Arial", Font.PLAIN, 24));
                euroText2.setForeground(Color.gray);

                //---- euroText3 ----
                euroText3.setText("\u20ac");
                euroText3.setFont(new Font("Arial", Font.PLAIN, 24));
                euroText3.setForeground(Color.gray);

                //---- addToListBtn2 ----
                addToListBtn2.setText("add to list");
                addToListBtn2.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                addToListBtn2.setForeground(Color.gray);
                addToListBtn2.setBorder(null);
                addToListBtn2.setFocusPainted(false);
                addToListBtn2.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\icons8-hinzuf\u00fcgen-48.png"));
                addToListBtn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addToListBtn2.setBackground(new Color(240, 240, 240));
                addToListBtn2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        addToListBtn2MouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        addToListBtn2MouseExited();
                    }
                });
                addToListBtn2.addActionListener(e -> addToListBtn2());

                //---- cancelBtn2 ----
                cancelBtn2.setText("cancel");
                cancelBtn2.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                cancelBtn2.setForeground(Color.gray);
                cancelBtn2.setBorder(null);
                cancelBtn2.setFocusPainted(false);
                cancelBtn2.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\icons8-stornieren-48.png"));
                cancelBtn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                cancelBtn2.setBackground(new Color(240, 240, 240));
                cancelBtn2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        cancelBtn2MouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        cancelBtn2MouseExited();
                    }
                });

                //---- addConfirmFeld ----
                addConfirmFeld.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                addConfirmFeld.setForeground(new Color(37, 190, 123));

                GroupLayout addSchedulePanelLayout = new GroupLayout(addSchedulePanel);
                addSchedulePanel.setLayout(addSchedulePanelLayout);
                addSchedulePanelLayout.setHorizontalGroup(
                    addSchedulePanelLayout.createParallelGroup()
                        .addGroup(addSchedulePanelLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addGroup(addSchedulePanelLayout.createParallelGroup()
                                .addGroup(addSchedulePanelLayout.createSequentialGroup()
                                    .addGroup(addSchedulePanelLayout.createParallelGroup()
                                        .addGroup(addSchedulePanelLayout.createSequentialGroup()
                                            .addComponent(ticketTyp2Cost, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(euroText2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(ticketCostText, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(addSchedulePanelLayout.createSequentialGroup()
                                            .addComponent(ticketTyp1Cost, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(euroText, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(startCityFeld, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(startCityText, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                                    .addGroup(addSchedulePanelLayout.createParallelGroup()
                                        .addComponent(desCityFeld, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(addSchedulePanelLayout.createParallelGroup()
                                            .addComponent(ticketTypComboBox1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ticketTypComboBox2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ticketTypComboBox3, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ticketTypText, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(disCityText, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE))
                                    .addGap(124, 124, 124))
                                .addGroup(addSchedulePanelLayout.createSequentialGroup()
                                    .addComponent(ticketTyp3Cost, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(euroText3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                                    .addGap(338, 338, 338))))
                        .addGroup(addSchedulePanelLayout.createSequentialGroup()
                            .addGroup(addSchedulePanelLayout.createParallelGroup()
                                .addGroup(addSchedulePanelLayout.createSequentialGroup()
                                    .addGap(116, 116, 116)
                                    .addComponent(addToListBtn2, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cancelBtn2, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
                                .addGroup(addSchedulePanelLayout.createSequentialGroup()
                                    .addGap(100, 100, 100)
                                    .addComponent(addConfirmFeld, GroupLayout.PREFERRED_SIZE, 440, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(150, Short.MAX_VALUE))
                );
                addSchedulePanelLayout.setVerticalGroup(
                    addSchedulePanelLayout.createParallelGroup()
                        .addGroup(addSchedulePanelLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(addSchedulePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(startCityText)
                                .addComponent(disCityText))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(addSchedulePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(startCityFeld, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addComponent(desCityFeld, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
                            .addGap(42, 42, 42)
                            .addGroup(addSchedulePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(ticketCostText)
                                .addComponent(ticketTypText))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(addSchedulePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(ticketTypComboBox1)
                                .addComponent(euroText, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                                .addComponent(ticketTyp1Cost, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(addSchedulePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(ticketTyp2Cost, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addComponent(ticketTypComboBox2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addComponent(euroText2, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(addSchedulePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(ticketTyp3Cost, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addComponent(ticketTypComboBox3, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addComponent(euroText3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(addConfirmFeld, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                            .addGroup(addSchedulePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addToListBtn2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cancelBtn2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28))
                );
            }
            parentPanel.add(addSchedulePanel, "card2");

            //======== removePanel ========
            {
                removePanel.setBackground(new Color(240, 240, 240));

                //---- removePanelText ----
                removePanelText.setText("Enter index of schedule to remove :");
                removePanelText.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                removePanelText.setForeground(Color.gray);

                //---- indexInputFeld ----
                indexInputFeld.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                indexInputFeld.setBackground(Color.white);
                indexInputFeld.setForeground(Color.gray);

                //---- cancelBtn ----
                cancelBtn.setText("cancel");
                cancelBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                cancelBtn.setForeground(Color.gray);
                cancelBtn.setBorder(null);
                cancelBtn.setFocusPainted(false);
                cancelBtn.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\icons8-stornieren-48.png"));
                cancelBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                cancelBtn.setBackground(new Color(240, 240, 240));
                cancelBtn.setHorizontalAlignment(SwingConstants.LEFT);
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

                //---- deleteScheduleBtn ----
                deleteScheduleBtn.setText("remove");
                deleteScheduleBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                deleteScheduleBtn.setForeground(Color.gray);
                deleteScheduleBtn.setBorder(null);
                deleteScheduleBtn.setFocusPainted(false);
                deleteScheduleBtn.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\icons8-unwiederuflich-l\u00f6schen-48.png"));
                deleteScheduleBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                deleteScheduleBtn.setBackground(new Color(240, 240, 240));
                deleteScheduleBtn.setHorizontalAlignment(SwingConstants.LEFT);
                deleteScheduleBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        deleteScheduleBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        deleteScheduleBtnMouseExited();
                    }
                });
                deleteScheduleBtn.addActionListener(e -> deleteScheduleBtn());

                //---- removeConfirmFeld ----
                removeConfirmFeld.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                removeConfirmFeld.setForeground(new Color(37, 190, 123));
                removeConfirmFeld.setPreferredSize(new Dimension(48, 44));

                GroupLayout removePanelLayout = new GroupLayout(removePanel);
                removePanel.setLayout(removePanelLayout);
                removePanelLayout.setHorizontalGroup(
                    removePanelLayout.createParallelGroup()
                        .addGroup(removePanelLayout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addGroup(removePanelLayout.createParallelGroup()
                                .addComponent(removeConfirmFeld, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
                                .addGroup(removePanelLayout.createSequentialGroup()
                                    .addComponent(indexInputFeld, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(deleteScheduleBtn, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
                                .addComponent(removePanelText, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(222, Short.MAX_VALUE))
                );
                removePanelLayout.setVerticalGroup(
                    removePanelLayout.createParallelGroup()
                        .addGroup(removePanelLayout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(removePanelText, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(removePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(indexInputFeld, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                .addComponent(deleteScheduleBtn))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(removeConfirmFeld, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(cancelBtn)
                            .addContainerGap(271, Short.MAX_VALUE))
                );
            }
            parentPanel.add(removePanel, "card3");

            //======== helpPanel ========
            {
                helpPanel.setBackground(new Color(240, 240, 240));

                //---- reportBtn ----
                reportBtn.setText("report a problem");
                reportBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                reportBtn.setForeground(Color.gray);
                reportBtn.setBorder(null);
                reportBtn.setFocusPainted(false);
                reportBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                reportBtn.setBackground(new Color(240, 240, 240));
                reportBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        reportBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        reportBtnMouseExited();
                    }
                });

                //---- supportCallBtn ----
                supportCallBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                supportCallBtn.setForeground(Color.gray);
                supportCallBtn.setBorder(null);
                supportCallBtn.setFocusPainted(false);
                supportCallBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                supportCallBtn.setBackground(new Color(240, 240, 240));
                supportCallBtn.setText("technical support");
                supportCallBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        supportCallBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        supportCallBtnMouseExited();
                    }
                });

                //---- sendEmailBtn ----
                sendEmailBtn.setFont(new Font("Century Gothic", Font.PLAIN, 24));
                sendEmailBtn.setForeground(Color.gray);
                sendEmailBtn.setBorder(null);
                sendEmailBtn.setFocusPainted(false);
                sendEmailBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                sendEmailBtn.setBackground(new Color(240, 240, 240));
                sendEmailBtn.setText("send an email");
                sendEmailBtn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        sendEmailBtnMouseEntered();
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        sendEmailBtnMouseExited();
                    }
                });

                //---- sendEmailIcon ----
                sendEmailIcon.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\email-64.png"));
                sendEmailIcon.setHorizontalAlignment(SwingConstants.CENTER);

                //---- supportCallIcon ----
                supportCallIcon.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\call-91.png"));
                supportCallIcon.setHorizontalAlignment(SwingConstants.CENTER);

                //---- reportIcon ----
                reportIcon.setIcon(new ImageIcon("F:\\JAVA\\IntelliJ-Workspace\\TicketAutomat\\report-65.png"));
                reportIcon.setHorizontalAlignment(SwingConstants.CENTER);

                GroupLayout helpPanelLayout = new GroupLayout(helpPanel);
                helpPanel.setLayout(helpPanelLayout);
                helpPanelLayout.setHorizontalGroup(
                    helpPanelLayout.createParallelGroup()
                        .addGroup(helpPanelLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(helpPanelLayout.createParallelGroup()
                                .addGroup(helpPanelLayout.createSequentialGroup()
                                    .addComponent(reportBtn, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(supportCallBtn, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
                                .addGroup(helpPanelLayout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addComponent(reportIcon, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                    .addGap(160, 160, 160)
                                    .addComponent(supportCallIcon, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(helpPanelLayout.createParallelGroup()
                                .addComponent(sendEmailBtn, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGroup(GroupLayout.Alignment.TRAILING, helpPanelLayout.createSequentialGroup()
                                    .addComponent(sendEmailIcon, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                    .addGap(63, 63, 63)))
                            .addGap(30, 30, 30))
                );
                helpPanelLayout.setVerticalGroup(
                    helpPanelLayout.createParallelGroup()
                        .addGroup(helpPanelLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addGroup(helpPanelLayout.createParallelGroup()
                                .addComponent(sendEmailIcon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addComponent(supportCallIcon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addComponent(reportIcon, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(helpPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(supportCallBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addComponent(sendEmailBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addComponent(reportBtn, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(401, Short.MAX_VALUE))
                );
            }
            parentPanel.add(helpPanel, "card4");
        }

        //======== sideBar ========
        {
            sideBar.setBackground(new Color(76, 143, 194));

            GroupLayout sideBarLayout = new GroupLayout(sideBar);
            sideBar.setLayout(sideBarLayout);
            sideBarLayout.setHorizontalGroup(
                sideBarLayout.createParallelGroup()
                    .addGap(0, 9, Short.MAX_VALUE)
            );
            sideBarLayout.setVerticalGroup(
                sideBarLayout.createParallelGroup()
                    .addGap(0, 577, Short.MAX_VALUE)
            );
        }

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addGap(0, 0, 0)
                    .addComponent(sideBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(parentPanel, GroupLayout.PREFERRED_SIZE, 716, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 1, Short.MAX_VALUE)
                            .addComponent(parentPanel, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE))
                        .addComponent(sidePanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                        .addComponent(sideBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // ------------------------------------------------------------
    // Start

    public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Control Panel");
        frame.setContentPane(new SchedulesManagementGUI());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}