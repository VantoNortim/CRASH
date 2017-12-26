package crashexam;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Provide a GUI interface for the job submission system.
 * Only shows a selection of possible functions: add job, list jobs waiting
 * record job done.
 * 
 * @author A.A.Marczyk
 * @version 20/10/17
 */
public class JobsGUI 
{
    private Management mmm = new Department("Welwyn","myprinters.txt");
    private JFrame myFrame = new JFrame("Jobs GUI");
    private JTextField name = new JTextField(20);
    private JLabel nameLabel = new JLabel ("Enter Customer name");
    private JTextField code = new JTextField(20);
    private JLabel codeLabel = new JLabel ("Enter Department/Course code");
    private JButton addButton = new JButton("Add");
    private JButton clearButton = new JButton("Clear");
    private ButtonGroup typeSelect = new ButtonGroup();
    private JRadioButton staff = new JRadioButton("Staff",true);
    private JRadioButton student = new JRadioButton("Student");
    private JCheckBox col = new JCheckBox("Colour");
    private JCheckBox qual = new JCheckBox("High Quality");
    private JCheckBox doub = new JCheckBox("Double-sided"); 
    private JLabel typeLabel = new JLabel("Customer Type");
    private JLabel printLabel = new JLabel("Copying");
    private JPanel eastPanel = new JPanel();
    private JPanel westPanel = new JPanel();
    private JPanel southPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel centrePanel = new JPanel();
    private JTextArea listing = new JTextArea();
    
    public JobsGUI()
    {
        makeFrame();
        makeMenuBar(myFrame);
    }
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {    
        myFrame.setLayout(new BorderLayout());
        myFrame.add(listing,BorderLayout.SOUTH);
        listing.setVisible(false);
        myFrame.add(eastPanel, BorderLayout.EAST);
        // set panel layout and add components
        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.add(addButton);
        eastPanel.add(clearButton);
        addButton.setVisible(false);
        addButton.addActionListener(new AddButtonHandler());
        makeCustomer();
        // building is done - arrange the components and show        
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar(JFrame frame)
    {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("Jobs");
        menubar.add(fileMenu);
        
        JMenuItem addItem = new JMenuItem("Add Job");
        addItem.addActionListener(new AddHandler());
        fileMenu.add(addItem);

        JMenuItem doneItem = new JMenuItem("Job Done");
        
        JMenuItem seeAll = new JMenuItem("List All");
         
    }

    private void makeCustomer()
    {
        typeSelect.add(staff);
        typeSelect.add(student);
        westPanel.setVisible(false);
        centrePanel.setVisible(false);
        northPanel.setVisible(false);
        myFrame.add(westPanel, BorderLayout.WEST);
        myFrame.add(centrePanel, BorderLayout.CENTER);
        myFrame.add(northPanel, BorderLayout.NORTH);
        // set panel layout and add components
        westPanel.setLayout(new GridLayout(4,1));
        centrePanel.setLayout(new GridLayout(4,1));
        northPanel.setLayout(new GridLayout(4,1));
        northPanel.add(nameLabel);
        northPanel.add(name);
        northPanel.add(codeLabel);
        northPanel.add(code);
        centrePanel.add(typeLabel);
        centrePanel.add(staff);  
        centrePanel.add(student);
        westPanel.add(printLabel);        
        westPanel.add(col); 
        westPanel.add(qual); 
        westPanel.add(doub); 
    }
    
    private class AddHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        { 
            westPanel.setVisible(true);
            centrePanel.setVisible(true);
            northPanel.setVisible(true);
            addButton.setVisible(true);
            listing.setVisible(false);
        }
    }
    
    private class AddButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {            
            String s = name.getText();
            int t = Integer.parseInt(code.getText());
            boolean st = staff.isSelected();
            boolean c = col.isSelected();
            boolean q = qual.isSelected();
            boolean d = doub.isSelected();
            String inputValue = JOptionPane.showInputDialog("Sides ?: ");
            int pag = Integer.parseInt(inputValue);
            inputValue = JOptionPane.showInputDialog("Copies?: ");
            int cop = Integer.parseInt(inputValue);
            
            int result = mmm.addJob(s,st,t,pag,cop,c,q,d);
            JOptionPane.showMessageDialog(myFrame,getResult(result));
            name.setText("");
            code.setText("");
            addButton.setVisible(false);
            westPanel.setVisible(false);
            centrePanel.setVisible(false);
            northPanel.setVisible(false);
        }
        
        public String getResult(int result)
        {
            switch (result)
            {
                case 1: return "Job printing";
                case 2: return "Job waiting";
                default:return "Job not accepted";
            }
        }
    }
    
    public static void main(String[] args)
    {
        new JobsGUI();
    }
}
   
