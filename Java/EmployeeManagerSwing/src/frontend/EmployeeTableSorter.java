package frontend;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * EmployeeTableSorter provides an active search to EmployeeTableModel based JTable.
 *
 * User know-how:
 * Use numeric value to search in seniority column
 * Use text value to search in name, surname and position column at the same time
 * Use > or < symbols with floating point value to search by bigger/lower value
 * than the typed one in salary column
 */
public class EmployeeTableSorter implements DocumentListener {
    final Pattern doublePattern;
    final TableRowSorter sorter;
    final JTextField filterText;

    public EmployeeTableSorter(TableRowSorter sorter, JTextField filterText) {
        this.sorter = sorter;
        this.filterText = filterText;
        this.doublePattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"); //extracts first found Double from text
    }

    private String getText() {
        return filterText.getText();
    }

    private void setLessFilter(String text) {
        Matcher matcher = doublePattern.matcher(text);

        if (matcher.find()) {
            double number = Double.parseDouble(matcher.group());
            sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.BEFORE, number, 4)); //column 4 - salary
        } else
            sorter.setRowFilter(null);
    }

    private void setMoreFilter(String text) {
        Matcher matcher = doublePattern.matcher(text);

        if (matcher.find()) {
            double number = Double.parseDouble(matcher.group());
            sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.AFTER, number, 4)); //column 4 - salary
        } else
            sorter.setRowFilter(null);
    }

    private void setNumericFilter(String text) {
        //setNumericFilter sets filter only for salary column

        if (text.charAt(0) == '<')
            setLessFilter(text);
        else if (text.charAt(0) == '>')
            setMoreFilter(text);
    }

    private void setTextFilter(String text) {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
    }

    private void setFilter() {
        String text = getText().trim();
        Matcher matcher = doublePattern.matcher(text);

        if (text.length() == 0)
            sorter.setRowFilter(null);
        else if (matcher.find())
            setNumericFilter(text);
        else
            setTextFilter(text);
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        setFilter();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        setFilter();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
