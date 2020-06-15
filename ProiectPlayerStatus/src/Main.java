public class Main {

    public static void main(String[] args) {
        PlayerStatus p1 = new PlayerStatus("P1", 50000, 3);
        PlayerStatus p2 = new PlayerStatus("P2", 50000, 3);

        //p1.findArtifact(7);
        //System.out.println(p1.getScore());

        p1.setWeaponInHand("sniper");
        p2.setWeaponInHand("kalashnikov");

        p1.movePlayerTo(4000, 4000);
        p2.movePlayerTo(224, 45);

        System.out.println(p1.shouldAttack(p2));
    }
}
