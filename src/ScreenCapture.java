import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class ScreenCapture {
    private static JFrame frame;
    private static JPanel panel;
    private static JFrame frame2;
    private static JPanel panel2;
    private static BufferedImage image;
    private static JComboBox<String> cb = new JComboBox<String>();

    public static String getSelectedValue() {
        return selectedValue;
    }

    private static String selectedValue;


    public static void initScreen() {
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(250,250,250,250));

        frame.add(panel, BorderLayout.CENTER);
        frame.setTitle("Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void initScreen2() {
        frame2 = new JFrame();
        panel2 = new JPanel();



        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(300, 100);
        frame2.setLocation(550, 0);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

        frame2.add(panel2);

        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<String> champions = new ArrayList<>();


        String[] choices = {"Aatrox", "Ahri", "Akali", "Alistar",
                "Amumu", "Anivia","Annie", "Aphelios", "Ashe", "Aurelion_Sol",
                "Azir", "Bard","Blitzcrank", "Brand", "Braum", "Caitlyn",
                "Camille", "Cassiopeia","Corki", "Darius", "Diana", "Dr._Mundo",
                "Draven", "Ekko","Elise", "Evelynn", "Fiddlesticks", "Fiora",
                "Fizz", "Galio","Gangplank", "Garen", "Gnar", "Gragas",
                "Graves", "Hecarim", "Heimerdinger", "Illaoi", "Irelia", "Ivern",
                "Janna", "Jax","Jayce", "Jhin", "Jinx", "Kalista",
                "Karma", "Karthus","Kassadin", "Katarina", "Kayle", "Kayn",
                "Kennen", "Kindred","Kled", "Leblanc", "Lee_Sin", "Leona",
                "Lillia", "Lissandra","Lucian", "Lux", "Malphite", "Malzahar",
                "Maokai", "Master_Yi","Miss_Fortune", "Mordekaiser", "Morgana", "Nami",
                "Nasus", "Nautilus", "Neeko", "Nidalee", "Nocturne", "Nunu",
                "Olaf", "Orianna","Ornn", "Pantheon", "Poppy", "Pyke",
                "Qiyana", "Quinn","Rakan", "Rammus", "Renekton", "Rengar",
                "Riven", "Rumble","Ryze", "Samira", "Sejuani", "Senna",
                "Seraphine", "Sett","Shaco", "Shen", "Shyvana", "Singed",
                "Sion", "Sivir","Skarner", "Sona", "Soraka", "Swain",
                "Sylas", "Syndra", "Tahm_Kench", "Taliyah", "Talon", "Taric",
                "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "Twisted_Fate",
                "Twitch", "Udyr","Urgot", "Varus", "Vayne", "Veigar",
                "Vi", "Viktor","Vladimir", "Volibear", "Warwick", "Wukong",
                "Xayah", "Xerath","Xin_Zhao", "Yasuo", "Yone", "Yorick",
                "Yuumi", "Zac", "Zed", "Ziggs", "Zilean", "Zoe",
                "Zyra"};


        JComboBox<String> cb = new JComboBox<>(choices);
        selectedValue = "Ezreal";

        cb.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedValue = cb.getSelectedItem().toString();
                System.out.println(selectedValue);
            }
        });


        cb.setMaximumSize(cb.getPreferredSize()); // added code
        cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
        panel2.add(cb);

        JButton btn = new JButton("OK");
        btn.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
        panel2.add(btn);

        frame2.setVisible(true); // added code

    }

    public static void updateScreen(BufferedImage screenImage) {
        if (image == null || image.getWidth() != screenImage.getWidth() || image.getHeight() != screenImage.getHeight()) {
            image = new BufferedImage(screenImage.getWidth(), screenImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        }

        Graphics2D g = image.createGraphics();
        g.drawImage(screenImage, 0, 0, null);
        g.dispose();

        Graphics2D panelGraphics = (Graphics2D) panel.getGraphics();
        panelGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        panelGraphics.drawImage(image, 0, 0, panel.getWidth(), panel.getHeight(), null);
        panelGraphics.dispose();
    }


}
