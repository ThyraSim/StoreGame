package config;


import dao.*;
import entities.*;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;


public class ConfigStartServlet extends HttpServlet {

    public void init() throws ServletException {

        if (JeuDao.findAll().isEmpty() && CommandeDao.findAll().isEmpty() && ClientDao.findAll().isEmpty() && CompteDao.findAll().isEmpty()) {

            Client client1 = new Client("admin", "admin", null, null);
            Client client2 = new Client("Beaudry", "Simon", "5150 rues des ormes", "Tyzral@gmail.com");
            Client client3 = new Client("Mercier", "Francis", "221B Baker Street", "Grimworld@gmail.com");
            Client client4 = new Client("Ami", "Ami", "50 rue l'Amitié", "ami@gmail.com");

            CompteDao.insert(new Compte("admin", "123", "Orignal"));
            CompteDao.insert(new Compte("Tyzral", "123", "Tyzral"));
            CompteDao.insert(new Compte("Grimworld", "123", "Grimworld"));
            CompteDao.insert(new Compte("THEFRIEND", "123", "ami"));


//            client1.setCompte(CompteDao.findCompteById(1));
//            client2.setCompte(CompteDao.findCompteById(2));
//            client3.setCompte(CompteDao.findCompteById(3));
//            client4.setCompte(CompteDao.findCompteById(4));

            ClientDao.insert(client1);
            ClientDao.insert(client2);
            ClientDao.insert(client3);
            ClientDao.insert(client4);

            GenreDao.insert(new Genre("Action-aventure"));
            GenreDao.insert(new Genre("Plateforme"));
            GenreDao.insert(new Genre("RPG"));
            GenreDao.insert(new Genre("Sandbox"));
            GenreDao.insert(new Genre("FPS"));
            GenreDao.insert(new Genre("Strategy"));
            GenreDao.insert(new Genre("Sports"));
            GenreDao.insert(new Genre("Simulation"));
            GenreDao.insert(new Genre("Puzzle"));
            GenreDao.insert(new Genre("Horror"));

            JeuDao.insert(new Jeu("Super Mario Odyssey", 59.99, GenreDao.findGenreById(2),
                    "Aidez Mario à sauver la princesse Peach dans une aventure trépidante à travers de nombreux royaumes."));
            JeuDao.insert(new Jeu("The Legend of Zelda: Breath of the Wild", 79.99, GenreDao.findGenreById(1),
                    "Explorez le vaste monde d'Hyrule en incarnant le héros Link et affrontez de redoutables ennemis."));
            JeuDao.insert(new Jeu("Minecraft", 29.99, GenreDao.findGenreById(4),
                    "Construisez et explorez un monde infini rempli de blocs, où seules vos limites sont votre créativité et votre imagination."));
            JeuDao.insert(new Jeu("The Witcher 3: Wild Hunt", 49.99, GenreDao.findGenreById(3),
                    "Incarnez Geralt de Riv, un puissant chasseur de monstres, et embarquez dans une quête épique à travers un monde fantastique rempli de dangers et de choix moraux."));
            JeuDao.insert(new Jeu("Rayman Legends", 39.99, GenreDao.findGenreById(2),
                    "Rejoignez Rayman et ses amis dans des niveaux colorés et remplis de défis pour sauver le monde des créatures maléfiques."));
            JeuDao.insert(new Jeu("Assassin's Creed Origins", 59.99, GenreDao.findGenreById(1),
                    "Découvrez l'histoire des origines de la confrérie des Assassins dans l'Égypte antique, et frayez-vous un chemin à travers des batailles épiques et des conspirations."));
            JeuDao.insert(new Jeu("Terraria", 19.99, GenreDao.findGenreById(4),
                    "Explorez, construisez et combattez dans un monde rempli de créatures mystérieuses et de trésors cachés."));
            JeuDao.insert(new Jeu("Final Fantasy XIV", 29.99, GenreDao.findGenreById(3),
                    "Plongez dans le monde de la saga légendaire Final Fantasy avec des milliers d'autres joueurs dans ce MMORPG riche en histoires et en combats épiques."));
            JeuDao.insert(new Jeu("Celeste", 19.99, GenreDao.findGenreById(2),
                    "Aidez Madeline à surmonter ses peurs intérieures et à escalader la montagne de Celeste dans ce jeu de plateforme acclamé."));
            JeuDao.insert(new Jeu("Red Dead Redemption 2", 59.99, GenreDao.findGenreById(1),
                    "Explorez le Far West américain à l'époque des hors-la-loi et participez à des braquages, des duels et des aventures épiques."));
            JeuDao.insert(new Jeu("Stardew Valley", 14.99, GenreDao.findGenreById(4),
                    "Quittez la ville pour hériter de la vieille ferme de votre grand-père et transformez-la en un lieu prospère en cultivant des cultures, en élevant du bétail et en tissant des liens avec les habitants de la ville."));
            JeuDao.insert(new Jeu("The Elder Scrolls V: Skyrim", 39.99, GenreDao.findGenreById(3),
                    "Un vaste monde ouvert avec des quêtes épiques, des dragons redoutables et des mystères à découvrir."));
            JeuDao.insert(new Jeu("Batman: Arkham City", 39.99, GenreDao.findGenreById(1),
                    "Incarnez Batman et combattez les super-vilains dans les rues sombres et corrompues d'Arkham City."));
            JeuDao.insert(new Jeu("Minecraft: Dungeons", 29.99, GenreDao.findGenreById(1),
                    "Plongez dans des donjons générés de manière procédurale, affrontez des monstres redoutables et obtenez des trésors légendaires dans ce jeu d'action-aventure inspiré de l'univers de Minecraft."));
            JeuDao.insert(new Jeu("RimWorld", 34.99, GenreDao.findGenreById(4),
                    "Gérez une colonie de survivants échoués sur une planète extraterrestre dans ce jeu de gestion de colonie complexe et captivant."));
            JeuDao.insert(new Jeu("Final Fantasy VII Remake", 59.99, GenreDao.findGenreById(3),
                    "Revivez l'histoire épique de Cloud Strife et du groupe de résistants Avalanche dans ce remake du célèbre jeu de rôle japonais."));
            JeuDao.insert(new Jeu("Crash Bandicoot N. Sane Trilogy", 39.99, GenreDao.findGenreById(2),
                    "Redécouvrez les aventures classiques de Crash Bandicoot dans une collection remasterisée comprenant les trois premiers jeux de la série."));
            JeuDao.insert(new Jeu("The Outer Worlds", 49.99, GenreDao.findGenreById(3),
                    "Explorez un univers de science-fiction dystopique, prenez des décisions morales difficiles et façonnez votre propre destinée dans ce RPG captivant."));
            JeuDao.insert(new Jeu("Super Smash Bros. Ultimate", 59.99, GenreDao.findGenreById(1),
                    "Affrontez vos personnages préférés de Nintendo et d'autres franchises dans des combats épiques mettant en scène des crossovers incroyables."));
            JeuDao.insert(new Jeu("Sekiro: Shadows Die Twice", 59.99, GenreDao.findGenreById(1),
                    "Affrontez des ennemis redoutables en tant que 'Loup à un bras' dans ce jeu d'action-aventure intense et exigeant développé par FromSoftware."));
            JeuDao.insert(new Jeu("Subnautica", 29.99, GenreDao.findGenreById(4),
                    "Plongez dans les profondeurs mystérieuses d'une planète océanique extraterrestre et survivez en explorant, en construisant et en découvrant les secrets de ce monde aquatique hostile."));
            JeuDao.insert(new Jeu("Divinity: Original Sin 2", 44.99, GenreDao.findGenreById(3),
                    "Plongez dans un vaste monde de fantasy et menez une équipe de héros dans une quête pour sauver le monde de la destruction dans ce RPG isométrique riche en choix et en conséquences."));
            JeuDao.insert(new Jeu("Doom Eternal", 39.99, GenreDao.findGenreById(5),
                    "Affrontez des hordes de démons infernaux dans ce jeu de tir frénétique et sanglant."));
            JeuDao.insert(new Jeu("Battlefield V", 49.99, GenreDao.findGenreById(5),
                    "Revivez les batailles emblématiques de la Seconde Guerre mondiale dans ce jeu de tir à la première personne."));
            JeuDao.insert(new Jeu("Madden NFL 23", 59.99, GenreDao.findGenreById(7),
                    "Prenez les commandes d'une équipe de football américain et menez-la vers la victoire ultime."));
            JeuDao.insert(new Jeu("Amnesia: Rebirth", 29.99, GenreDao.findGenreById(10),
                    "Plongez dans une histoire terrifiante et découvrez les sombres secrets qui vous entourent."));
            JeuDao.insert(new Jeu("NBA 2K23", 59.99, GenreDao.findGenreById(7),
                    "Dominez le terrain de basket et vivez l'expérience de la NBA avec des joueurs célèbres."));
            JeuDao.insert(new Jeu("Total War: Warhammer II", 49.99, GenreDao.findGenreById(6),
                    "Prenez le contrôle d'une des factions fantastiques de l'univers de Warhammer et dominez le monde."));
            JeuDao.insert(new Jeu("Microsoft Flight Simulator", 59.99, GenreDao.findGenreById(8),
                    "Envolez-vous dans les cieux et explorez le monde entier avec des détails incroyablement réalistes."));
            JeuDao.insert(new Jeu("FIFA 23", 59.99, GenreDao.findGenreById(7),
                    "Vivez l'excitation du football avec des graphismes réalistes et une jouabilité améliorée."));
            JeuDao.insert(new Jeu("XCOM 2", 39.99, GenreDao.findGenreById(6),
                    "Dirigez une force d'élite pour repousser une invasion extraterrestre dans ce jeu de stratégie au tour par tour."));
            JeuDao.insert(new Jeu("Resident Evil Village", 59.99, GenreDao.findGenreById(10),
                    "Affrontez des horreurs surnaturelles dans ce jeu de survie et d'action à la troisième personne."));
            JeuDao.insert(new Jeu("Tetris Effect", 39.99, GenreDao.findGenreById(9),
                    "Découvrez une expérience de Tetris unique avec des graphismes et des effets audiovisuels immersifs."));
            JeuDao.insert(new Jeu("Civilization VI", 59.99, GenreDao.findGenreById(6),
                    "Construisez votre civilisation et menez-la à la gloire à travers les âges dans ce jeu de stratégie."));
            JeuDao.insert(new Jeu("Euro Truck Simulator 2", 29.99, GenreDao.findGenreById(8),
                    "Devenez un routier professionnel et parcourez l'Europe dans ce jeu de simulation de conduite."));
            JeuDao.insert(new Jeu("Dead by Daylight", 29.99, GenreDao.findGenreById(10),
                    "Faites face à un tueur impitoyable dans ce jeu multijoueur asymétrique où la survie est primordiale."));
            JeuDao.insert(new Jeu("The Witness", 29.99, GenreDao.findGenreById(9),
                    "Explorez une île mystérieuse remplie de puzzles complexes et découvrez ses secrets."));
            JeuDao.insert(new Jeu("The Sims 4", 39.99, GenreDao.findGenreById(8),
                    "Créez et contrôlez des vies virtuelles dans ce jeu de simulation de vie réaliste."));
            JeuDao.insert(new Jeu("Portal 2", 19.99, GenreDao.findGenreById(9),
                    "Résolvez des énigmes et manipulez l'espace avec votre pistolet de portail dans ce jeu de puzzle captivant."));
            JeuDao.insert(new Jeu("Call of Duty: Modern Warfare", 59.99, GenreDao.findGenreById(5),
                    "Plongez dans une guerre moderne avec des graphismes époustouflants et une expérience multijoueur intense."));
            //recap
            //compte 1 est admin tous les jeux ( 1 commande avec all game)
            //compte 2 est simon 1 commande ancienne + 1 commande en cours (panier)
            //compte 3 est Francis comme un nouvel utilisateur avec 0 commande et 0 panier
            //compte 4 est ami simule pour recevoir les cadeaux ( aucune historique de commande)


            //COMPTE 1 ADMIN

            //Donner tous les jeux à l'admin pour le test profile
            Commande commande1 = new Commande(CompteDao.findCompteById(1),false);
            CommandeDao.insert(commande1);
            //ajoute tous les jeux existant
            for (Jeu jeu:
                 JeuDao.findAll()) {
                commande1.addJeu(jeu);
            }


            //COMPTE 2 SIMON

            // assignation d'une ancienne commande (2)
            Commande commande2 = new Commande(CompteDao.findCompteById(2), false);
            // insère la commande dans la bdd
            CommandeDao.insert(commande2);
            //simuler des ajout de jeux dans bdd
            commande2.addJeu(JeuDao.findJeuById(1));
            commande2.addJeu(JeuDao.findJeuById(5));
            commande2.addJeu(JeuDao.findJeuById(9));

            // Dernière commande acticve commande 3
            Commande commande3 = new Commande(CompteDao.findCompteById(2), true);
            // insère la commande dans la bdd
            CommandeDao.insert(commande3);
            //Ajout 1 jeu
            CommandeDao.insert(commande3);
            commande3.addJeu(JeuDao.findJeuById(12));



        }
    }
}








