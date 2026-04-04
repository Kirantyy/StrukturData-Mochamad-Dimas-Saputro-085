import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

class Siswa {
    int n; String m;
    Siswa(int n, String m) { this.n = n; this.m = m; }
}

public class AntrianSekolah extends JFrame {
    Queue<Siswa> q = new LinkedList<>();
    int count = 1;
    JTextField txt = new JTextField();
    DefaultTableModel md = new DefaultTableModel(new String[]{"No", "Nama Siswa"}, 0);

    public AntrianSekolah() {
        setTitle("Antrian Sekolah FST - Logat Indo");
        setSize(400, 500);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        JPanel p = new JPanel(new GridLayout(4,1,5,5));
        p.add(new JLabel(" Masukkan Nama:"));
        p.add(txt);
        JButton b1 = new JButton("AMBIL");
        JButton b2 = new JButton("PANGGIL");
        p.add(b1); pnlStyle(b1, new Color(46, 204, 113));
        p.add(b2); pnlStyle(b2, new Color(52, 152, 219));

        add(p, BorderLayout.NORTH);
        add(new JScrollPane(new JTable(md)), BorderLayout.CENTER);

        b1.addActionListener(e -> {
            if(!txt.getText().isEmpty()){
                q.add(new Siswa(count, txt.getText()));
                count++; txt.setText(""); update();
            }
        });

        b2.addActionListener(e -> {
            if(!q.isEmpty()){
                Siswa s = q.poll();
                update();
                String teks = "Nomor antrian " + s.n + ", " + s.m + ", silakan ke loket.";
                
                // JALANKAN SUARA LOGAT INDONESIA
                new Thread(() -> {
                    try {
                        // Perintah PS ini mencari suara yang support bahasa Indonesia
                        String psCmd = "Add-Type -AssemblyName System.Speech; " +
                                     "$s = New-Object System.Speech.Synthesis.SpeechSynthesizer; " +
                                     "$voice = $s.GetInstalledVoices() | Where-Object {$_.VoiceInfo.Culture.Name -eq 'id-ID'} | Select-Object -First 1; " +
                                     "if($voice) { $s.SelectVoice($voice.VoiceInfo.Name) }; " +
                                     "$s.Speak('" + teks + "')";
                        
                        new ProcessBuilder("powershell", "-Command", psCmd).start();
                    } catch (Exception ex) { Toolkit.getDefaultToolkit().beep(); }
                }).start();

                JOptionPane.showMessageDialog(this, teks);
            }
        });
    }

    void pnlStyle(JButton b, Color c) {
        b.setBackground(c); b.setForeground(Color.WHITE); b.setFocusPainted(false);
    }

    void update() {
        md.setRowCount(0);
        for(Siswa s : q) md.addRow(new Object[]{s.n, s.m});
    }

    public static void main(String[] args) {
        new AntrianSekolah().setVisible(true);
    }
}