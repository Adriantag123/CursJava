public class PlayerStatus {

    private String nickname;
    private int score;
    private int lives;
    private int health;
    private String weaponInHand;

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public int getHealth() {
        return this.health;
    }

    private double positionX;
    private double positionY;

    public String getNickname() {
        return nickname;
    }

    protected static String getGameName() {
        return gameName;
    }

    protected static void setGameName(String gameName) {
        PlayerStatus.gameName = gameName;
    }

    private static String gameName;

    public PlayerStatus(String nickname) {
        this.nickname = nickname;
    }

    public PlayerStatus(String nickname, int lives) {
        this.nickname = nickname;
        this.lives = lives;
    }

    public PlayerStatus(String nickname, int score, int lives) {
        this.nickname = nickname;
        this.score = score;
        this.lives = lives;
        this.positionX = 0;
        this.positionY = 0;
    }

    public void findArtifact(int artifactCode){
        if(isPerfect(artifactCode)){
            this.score += 5000;
            this.lives++;
            this.health = 100;
            return;
        }

        if(isPrime(artifactCode)){
            this.score += 1000;
            this.lives += 2;
            this.health += 25;
            if(this.health > 100){
                this.health = 100;
            }
            return;
        }

        if(isCapcana(artifactCode)){
            this.score -= 3000;
            this.health -= 25;
            if(this.health <= 0){
                this.lives--;
                this.health = 100;
            }
            return;
        }

        this.score += artifactCode;
    }

    public boolean isPerfect(int number){
        int d = 1;
        int sum = 0;
        while(d*d <= number){
            if(number % d == 0){
                sum += d;
                if(number / d != d){
                    sum += d;
                }
            }
            d++;
        }
        if(sum - number == number){
            return true;
        }
        return false;
    }

    public boolean isPrime(int number){
        int d = 2;
        while( d * d <= number) {
            if(number % d == 0){
                return false;
            }
            d++;
        }
        if(number == 0 || number == 1){
            return false;
        }
        return true;
    }

    public boolean isCapcana(int number){
        int sum = 0;
        if(number % 2 == 0) {
            while(number != 0){
                sum += number % 10 ;
                number /= 10;
            }
        }
        if(sum / 3 == 0){
            return true;
        }
        return false;
    }

    public boolean setWeaponInHand(String weaponInHand){
        if(weaponInHand.equals("knife") && this.score >= 1000){
            this.score -= 1000;
            this.weaponInHand = "knife";
            return true;
        }

        if(weaponInHand.equals("kalashnikov") && this.score >= 20000){
            this.score -= 20000;
            this.weaponInHand = "kalashnikov";
            return true;
        }

        if(weaponInHand.equals("sniper") && this.score >= 10000){
            this.score -= 10000;
            this.weaponInHand = "sniper";
            return true;
        }
        return false;
    }

    public String getWeaponInHand() {
        return weaponInHand;
    }

    public void movePlayerTo(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }


    public int getScore() {
        return this.score;
    }

    public static double distance(PlayerStatus p1, PlayerStatus p2){
        return Math.sqrt(((p1.getPositionX() - p2.getPositionX()) * (p1.getPositionX() - p2.getPositionX()) +
                ((p1.getPositionY() - p2.getPositionY()) * (p1.getPositionY() - p2.getPositionY()))));
    }

    public boolean shouldAttack(PlayerStatus opponent){

        if(this.getWeaponInHand().equals(opponent.getWeaponInHand())){
            double p1, p2;
            p1 = (3 * this.health + this.score / 1000) / 4;
            p2 = (3 * opponent.getHealth() + opponent.getScore() / 1000) / 4;

            if(p1 > p2){
                return true;
            } else {
                return false;
            }
        } else {

            if(distance(this,opponent) > 1000){
                if(this.getWeaponInHand().equals("sniper")){
                    return true;
                }

                else if(this.getWeaponInHand().equals("kalashnikov")){
                    if(opponent.getWeaponInHand().equals("sniper")){
                        return false;
                    }
                    return true;
                }

                else {
                    return false;
                }
            } else  {
                if(this.getWeaponInHand().equals("kalashnikov")){
                    return true;
                }

                else if (this.getWeaponInHand().equals("sniper")){
                    if(opponent.getWeaponInHand().equals("kalashnikov")){
                        return false;
                    }
                    return true;
                }

                else {
                    return false;
                }
            }
        }
    }
}

