// 戦士
class Warrior extends Human {
    public Warrior(String name) {
        super(name);
        /* hp, atk, spd などの初期値を決める */
    }
    @Override
    public String attack(Enemy target) { return name + "の剣撃！"; /* ダメージ計算を書く */ }
    @Override
    public String magic(Enemy target) { return name + "は魔法が使えない！"; }
    @Override
    public String skill(Enemy target) { return name + "の渾身の一撃！"; }
    @Override
    public boolean escape() { return false; /* 成功判定を書く */ }
}

// 騎士
class Knight extends Human {
    public Knight(String name) {
        super(name);
        /* 値を決める */
    }
    @Override
    public String attack(Enemy target) { return name + "の突き！"; }
    @Override
    public String magic(Enemy target) { return ""; }
    @Override
    public String skill(Enemy target) { return name + "のシールドバッシュ！"; }
    @Override
    public boolean escape() { return false; }
}

// 魔法使い
class Wizard extends Human {
    public Wizard(String name) {
        super(name);
        /* 値を決める */
    }
    @Override
    public String attack(Enemy target) { return name + "で叩く！"; }
    @Override
    public String magic(Enemy target) { return name + "の火炎魔法！"; }
    @Override
    public String skill(Enemy target) { return name + "の魔力暴走！"; }
    @Override
    public boolean escape() { return false; }
}

// 僧侶
class Monk extends Human {
    public Monk(String name) {
        super(name);
        /* 値を決める */
    }
    @Override
    public String attack(Enemy target) { return name + "の正拳突き！"; }
    @Override
    public String magic(Enemy target) { return name + "の光の魔法！"; }
    @Override
    public String skill(Enemy target) { return name + "の天罰！"; }
    @Override
    public boolean escape() { return false; }
}
