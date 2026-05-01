// アイテム親クラス
abstract class Item {
    String name;
    int num;
    public abstract String effect(Human user, Enemy target);
}

// 薬草
class Herb extends Item {
    public Herb() { this.name = "薬草"; this.num = 5; }
    @Override
    public String effect(Human user, Enemy target) {
        /* 回復処理を書く */
        this.num--;
        return name + "で回復した！";
    }
}

// 爆弾
class Bomb extends Item {
    public Bomb() { this.name = "爆弾"; this.num = 2; }
    @Override
    public String effect(Human user, Enemy target) {
        /* ダメージ処理を書く */
        this.num--;
        return name + "を投げた！敵に大ダメージ！";
    }
}
