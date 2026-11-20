package DungeonGame;

class Entity {
 
 // Attributes
 String name;
 int maxhealth, currenthealth, maxenergy, currentenergy;
 int strengthdebuff;
 
 // items
 String weapon;
 int weapondamage;
 
 
 // Attribute Constructor
 public Entity(String name, int maxhealth, int maxenergy, int weapondamage) {
 this.name = name;
 this.maxhealth = maxhealth;
 this.maxenergy = maxenergy;
 this.weapondamage = weapondamage;
 this.currenthealth = maxhealth;
 this.currentenergy = maxenergy;
 }
 
 // To use Strength Potion
 int useStrength(int strengthnum) {
 if (strengthnum > 0) {
 System.out.println("You have gained a Strength buff!");
 strengthnum--;
 
 if (strengthdebuff > 0) {
 System.out.println("Strength buff refreshed!");
 } else {
 System.out.println("Strength buff applied!");
 }
 
 // Refresh duration
 strengthdebuff = 3;
 weapondamage += 25;
 } else {
 System.out.println("You are out of Strength potions");
 }
 return strengthnum;
 }
 
 // strength de-buff counter
 int StrengthDebuff() {
 if (strengthdebuff > 0) {
 strengthdebuff--;
 if (strengthdebuff == 0) {
 weapondamage -= 25;
 System.out.println(name + "'s Strength Potion has worn off!");
 }
 }
 return strengthdebuff;
 }
 
 // To use Health Potion
 int useHealth(int healthnum) {
 if (healthnum > 0) {
 System.out.println("Restored full Health!");
 healthnum --;
 currenthealth = maxhealth;
 } else {
 System.out.println("You are out of Health potions");
 }
 return healthnum;
 }
 
 // To use Energy Potion
 int useEnergy(int energynum) {
 if (energynum > 0) {
 System.out.println("Restored full Energy!");
 energynum --;
 currentenergy = maxenergy;
 } else {
 System.out.println("You are out of Energy potions");
 }
 return energynum;
 }
 }