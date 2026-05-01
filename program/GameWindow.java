import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameWindow extends JFrame {
    private Human player;
    private Enemy currentEnemy;
    private Random rand = new Random();

    // GUIパーツ
    private JTextArea logArea = new JTextArea();
    private JLabel statusLabel = new JLabel();
    private JButton btnAttack = new JButton("攻撃");
    private JButton btnMagic = new JButton("魔法");
    private JButton btnSkill = new JButton("スキル");
    private JButton btnEscape = new JButton("逃げる");

    public GameWindow() {
        // ① ジョブを選択する（ダイアログ）
        String[] jobs = {"戦士", "騎士", "魔法使い", "僧侶"};
        int select = JOptionPane.showOptionDialog(null, "ジョブを選んでください", "Job Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, jobs, jobs[0]);
        
        switch(select) {
            case 0: player = new Warrior("勇者"); break;
            case 1: player = new Knight("勇者"); break;
            case 2: player = new Wizard("勇者"); break;
            case 3: player = new Monk("勇者"); break;
            default: player = new Warrior("勇者");
        }

        // GUIの初期設定
        setTitle("Java Bronze RPG v1.0");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea.setEditable(false);
        logArea.setFont(new Font("MS ゴシック", Font.PLAIN, 16));
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        JPanel pnlStatus = new JPanel();
        pnlStatus.add(statusLabel);
        add(pnlStatus, BorderLayout.NORTH);

        JPanel pnlAction = new JPanel();
        pnlAction.add(btnAttack);
        pnlAction.add(btnMagic);
        pnlAction.add(btnSkill);
        pnlAction.add(btnEscape);
        add(pnlAction, BorderLayout.SOUTH);

        // ボタンイベント
        btnAttack.addActionListener(e -> turn("attack"));
        btnMagic.addActionListener(e -> turn("magic"));
        btnSkill.addActionListener(e -> turn("skill"));
        btnEscape.addActionListener(e -> turn("escape"));

        spawnEnemy();
    }

    // ② 敵の出現（レベルに応じて出現率を変えるロジックをここに書く）
    private void spawnEnemy() {
        int r = rand.nextInt(100);
        // ここで player.lv を参照して敵の種類を決める
        if (player.lv >= 10 && r < 20) {
            currentEnemy = new Demon();
        } else if (r < 50) {
            currentEnemy = new Slime();
        } else {
            currentEnemy = new Dragon();
        }
        logArea.append(">> " + currentEnemy.name + "が現れた！\n");
        updateDisplay();
    }

    // ③ ターン制ループの実行
    private void turn(String cmd) {
        String pMsg = "";
        boolean isEscaped = false;

        // ④ コマンド選択後のプレイヤー行動
        switch(cmd) {
            case "attack": pMsg = player.attack(currentEnemy); break;
            case "magic":  pMsg = player.magic(currentEnemy); break;
            case "skill":  pMsg = player.skill(currentEnemy); break;
            case "escape": 
                if (player.escape()) { pMsg = "逃走成功！"; isEscaped = true; }
                else { pMsg = "逃走失敗！"; }
                break;
        }
        logArea.append(pMsg + "\n");

        // ⑤ 終了判定
        if (currentEnemy.hp <= 0 || isEscaped) {
            if (currentEnemy.hp <= 0) {
                logArea.append(currentEnemy.name + "を倒した！\n");
                // ⑥ 魔王を倒したら終了
                if (currentEnemy instanceof Demon) {
                    JOptionPane.showMessageDialog(this, "魔王撃破！ゲームクリア！");
                    System.exit(0);
                }
                // レベルアップ判定などをここに書く
                player.levelUp();
                logArea.append("レベルアップ！ Lv:" + player.lv + "\n");
            }
            spawnEnemy(); // ⑥ 再度敵が選ばれる
        } else {
            // 敵のターン
            logArea.append(currentEnemy.attack(player) + "\n");
            if (player.hp <= 0) {
                JOptionPane.showMessageDialog(this, "ゲームオーバー...");
                System.exit(0);
            }
        }
        updateDisplay();
    }

    private void updateDisplay() {
        statusLabel.setText(String.format("【%s】Lv:%d HP:%d/%d | 【%s】HP:%d", 
            player.name, player.lv, player.hp, player.maxHp, currentEnemy.name, currentEnemy.hp));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameWindow().setVisible(true));
    }
}
