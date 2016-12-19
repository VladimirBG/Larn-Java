package ru.techcoll.swords.domain;

/**
 * Класс-прототип игрока. Содержит значения по умолчанию, используемые для
 * инициализации новых игроков.
 * Значения полей таких классов должны считываться из файлов шаблонов/конфигурации,
 * например, в формате XML
 */
public class PlayerProto {

    private PlayerProto() { }

    public int getInitialGold() {
        return 1000;
    }

    public String[] getDefaultRoster() {
        return new String[] {
            Unit.PIKEMAN, Unit.PIKEMAN, Unit.PIKEMAN,
            Unit.ARCHER, Unit.ARCHER,
            Unit.KNIGHT,
            Unit.MAGE,
            Unit.GRIFFIN
        };
    }

    public String getDefaultRosterName() {
        return "Delta Ops";
    }


    private static PlayerProto instance = new PlayerProto();

    public static PlayerProto getInstance() {
        return instance;
    }

}
