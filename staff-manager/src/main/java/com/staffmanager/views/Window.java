package com.staffmanager.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.staffmanager.models.Employee;
import com.staffmanager.services.EmployeeCRUDService;

public class Window extends JFrame {
    private JTextField id;
    private JTextField name;
    private JTextField salary;
    private JComboBox<String> gender;

    private JButton save, load, delete, viewExec;

    private JLabel label1, idLbl, nameLbl, salaryLbl, genderLbl;

    private Employee employee;
    private EmployeeCRUDService crudService;

    // private JPanel buttonPanel, formPanel, container;

    public Window() {
        this.setLayout(new GridBagLayout());
        initializeComponents();
        addComponentsToFrame();
        addListeners();
        setProperties();
    }

    private void initializeComponents() {
        employee = new Employee();
        try {
            crudService = new EmployeeCRUDService();
        } catch (SQLException e) {
           logger.fatal("Can't connect to database! " + e.getMessage());
            e.printStackTrace();
        }

        id = new JTextField();
        id.setVisible(true);

        name = new JTextField();
        name.setVisible(true);

        salary = new JTextField();
        salary.setVisible(true);

        String[] genderOptions = { "Male", "Female" };
        gender = new JComboBox<>(genderOptions);
        gender.setVisible(true);

        save = new JButton("Save");
        save.setVisible(true);

        load = new JButton("Load");
        load.setVisible(true);

        delete = new JButton("Delete");
        delete.setVisible(true);

        viewExec = new JButton("View Exec");
        viewExec.setVisible(true);

        label1 = new JLabel("Employee Database");
        label1.setVisible(true);

        idLbl = new JLabel("Id:");
        idLbl.setVisible(true);

        nameLbl = new JLabel("Name:");
        nameLbl.setVisible(true);
        
        salaryLbl = new JLabel("Salary:");
        salaryLbl.setVisible(true);

        genderLbl = new JLabel("Gender:");
        genderLbl.setVisible(true);

    }

    private void addComponentsToFrame() {
        GridBagConstraints constraints = new GridBagConstraints();
        Insets insets = new Insets(0,0,0,0);

        constraints.gridy = 0;
        this.add(label1, constraints);

        insets.right = 20;
        insets.top = 10;
        insets.bottom = 10;
        insets.left = 10;
        constraints.insets = insets;

        constraints.gridy = 1;
        constraints.gridx = 0;
        this.add(idLbl, constraints);

        constraints.gridx = 1;
        this.add(id, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        this.add(nameLbl, constraints);

        constraints.gridx = 1;
        this.add(name, constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        this.add(salaryLbl, constraints);

        constraints.gridx = 1;
        this.add(salary, constraints);

        constraints.gridy = 4;
        constraints.gridx = 0;
        this.add(genderLbl, constraints);

        constraints.gridx = 1;
        this.add(gender, constraints);

        insets.right = 20;
        insets.top = 30;
        insets.bottom = 30;
        insets.left = 10;
        constraints.insets = insets;

        constraints.gridy = 5;
        constraints.gridx = 0;
        this.add(nameLbl, constraints);

        constraints.gridx = 1;
        this.add(name, constraints);
    }

    private void addListeners() {
        id.addActionListener(new ActionListener() {
              @Override
            public void actionPerformed(ActionEvent a) {
                employee.setId(id.getText().trim());
            }
        });

        name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                employee.setName(name.getText().trim());
            }
        });

        salary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    Double value = Double.parseDouble(salary.getText().trim());
                    employee.setSalary(value);
                } catch (Exception e) {
                    employee.setSalary(0.0);
                    new JOptionPane("Error setting salary. Invalid Number!", JOptionPane.ERROR_MESSAGE);                                    
                }

            }
        });

        gender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                employee.setGender((String) gender.getSelectedItem());
            }
        });

        // buttons
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                try {
                    crudService.insert(employee);
                } catch (Exception e) {
                    logger.error("Error Loading Tabe Rows! " + e);
                    new JOptionPane("Error adding record!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewExec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {

                try {
                    ArrayList<Employee> emps = crudService.readAllExec();
                    String message = emps.toString().replaceAll(",", "\n");
                    new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    logger.error("Error Loading Tabe Rows! " + e);
                    new JOptionPane("Error loading records!", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(Action a) {
                try {
                    employee = crudService.read(employee.getId());
                } catch (Exception e) {
                    logger.error("Error Loading Tabe Row! " + e);
                    new JOptionPane("Error loading record!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(Action a) {
                try {
                    crudService.delete(employee.getId());
                } catch (Exception e) {
                    logger.error("Error Deleting Tabe Row! " + e);
                    new JOptionPane("Error deleting record!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setProperties(){
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }
}