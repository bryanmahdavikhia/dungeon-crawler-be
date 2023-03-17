# DungeonCrawlerBE

Master pipeline
[![coverage report](https://gitlab.cs.ui.ac.id/AdvProg/reguler-2022/student/kelas-a/2006596075-Muhammad-Haqqi-Al-Farizi/a1-dungeon-crawler-game/dungeon-crawler-be/badges/master/coverage.svg)](https://gitlab.cs.ui.ac.id/AdvProg/reguler-2022/student/kelas-a/2006596075-Muhammad-Haqqi-Al-Farizi/a1-dungeon-crawler-game/dungeon-crawler-be/-/commits/master)

Staging pipeline
[![coverage report](https://gitlab.cs.ui.ac.id/AdvProg/reguler-2022/student/kelas-a/2006596075-Muhammad-Haqqi-Al-Farizi/a1-dungeon-crawler-game/dungeon-crawler-be/badges/staging/coverage.svg)](https://gitlab.cs.ui.ac.id/AdvProg/reguler-2022/student/kelas-a/2006596075-Muhammad-Haqqi-Al-Farizi/a1-dungeon-crawler-game/dungeon-crawler-be/-/commits/staging)

Sprint 1:
- Project initialization.
- Configure SonarCube.
- Configure gitlab pipeline and heroku procfile.
- Add Account class for permanent user data.
- Add Hero class, HeroClass interface and it's concrete implementation (Swordsman, Mage, Archer).
- Add Enemy Interface, MonsterAbstract(Implement Enemy), Monster and BossMonster class (Extend from Monster Abstract).
- Add Item Interface, HealthPotion and ManaPotion (Implement Item), and ItemFactory class.
- Add Armor and Sword interface and it's implementation.
- Add AccountRepository, AccountService, and AccountController to handle Register Account and Get Account Endpoints ("/account/" and "/account/create").
- Create DTO's for AccountController request format.
- Add HeroService to create Hero instance from Account data.
- These Core element's (Hero, Enemy, Item, Equipments) methods are not completely finished yet.
- Add unit test.

Sprint 2:
- Remove some unnecessary attributes from Hero class. Complete all methods implementation. Now Hero class is fully functional.
- Complete all HeroClass concrete class implementation.
- Remove MonsterAbstract, now all monster directly implements Enemy interface. Add MonsterFactory to help dungeon create different monsters based on how many monsters are left in the dungeon.
- Complete all Monster BossMonster method implementation. Now fully functional.
- Minor change in Item's concrete class implementation (HealthPotion and ManaPotion).
- Minor change in Armor and Weapon implementation.
- Create Dungeon class and fully implement it.
- Create DungeonRepository. Dungeons are stored in non persistent memory.
- Create DungeonService to handle dungeon interaction from bot request.
- Create DungeonController to catch bot request in these endpoints:
	- /dungeon/start
	- /dungeon/attack
	- /dungeon/item
	- /dungeon/useitem
	- /dungeon/skill
	- /dungeon/leave
	- /dungeon/continue
	- /dungeon/timeout
- Create DTO's for DungeonController request format.
- Add more unit test.
