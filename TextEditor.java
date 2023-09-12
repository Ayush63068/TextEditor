
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TextEditor extends JFrame implements ActionListener {
    static JFrame f;
    static JMenuBar mb;
    static JMenu file;
    static JMenu edit;
    static JMenuItem newfile, openfile, savefile, cut, copy, paste, selectAll, close;
    static JTextArea textArea;

    TextEditor() {
        f = new JFrame("TextEditor");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mb = new JMenuBar();
        file = new JMenu("file");
        edit = new JMenu("edit");
        newfile = new JMenuItem("new");
        openfile = new JMenuItem("open");
        savefile = new JMenuItem("save");
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("select All");
        close = new JMenuItem("close");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        mb.add(file);
        mb.add(edit);
        f.setJMenuBar(mb);
        textArea = new JTextArea();
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        panel.add(textArea, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        f.add(panel);
        f.setBounds(100, 100, 500, 500);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new TextEditor();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut) {
            textArea.cut();
        } else if (e.getSource() == copy) {
            textArea.copy();
        } else if (e.getSource() == paste) {
            textArea.paste();
        } else if (e.getSource() == selectAll) {
            textArea.selectAll();
        } else if (e.getSource() == close) {
            System.exit(0);
        }

        if (e.getSource() == openfile) {
            JFileChooser fileChooser = new JFileChooser("Home:");
            int chooser = fileChooser.showOpenDialog(null);
            if (chooser == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filepath = file.getPath();
                try {
                    FileReader fileReader = new FileReader(filepath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = "", output = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        output = output + line + "\n";
                    }

                    textArea.setText(output);
                    bufferedReader.close();
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, ee.toString());
                }

            }
        }
        if (e.getSource() == savefile) {
            JFileChooser fileChooser = new JFileChooser("Home:");
            int chooser = fileChooser.showSaveDialog(null);
            if (chooser == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (Exception ee) {
                    JOptionPane.showMessageDialog(null, ee.toString());
                }

            }
        }
        if (e.getSource() == newfile) {
            new TextEditor();
        }
    }
}
