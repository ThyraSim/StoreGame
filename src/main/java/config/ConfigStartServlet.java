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


            client1.setCompte(CompteDao.findCompteById(1));
            client2.setCompte(CompteDao.findCompteById(2));
            client3.setCompte(CompteDao.findCompteById(3));
            client4.setCompte(CompteDao.findCompteById(4));

            ClientDao.insert(client1);
            ClientDao.insert(client2);
            ClientDao.insert(client3);
            ClientDao.insert(client4);

            JeuDao.insert(new Jeu("Super Mario Odyssey", 59.99, "Plateforme",
                    "Aidez Mario à sauver la princesse Peach dans une aventure trépidante à travers de nombreux royaumes."));
            JeuDao.insert(new Jeu("The Legend of Zelda: Breath of the Wild", 79.99, "Action-aventure",
                    "Explorez le vaste monde d'Hyrule en incarnant le héros Link et affrontez de redoutables ennemis."));
            JeuDao.insert(new Jeu("Minecraft", 29.99, "Sandbox",
                    "Construisez et explorez un monde infini rempli de blocs, où seules vos limites sont votre créativité et votre imagination."));
            JeuDao.insert(new Jeu("The Witcher 3: Wild Hunt", 49.99, "RPG",
                    "Incarnez Geralt de Riv, un puissant chasseur de monstres, et embarquez dans une quête épique à travers un monde fantastique rempli de dangers et de choix moraux."));
            JeuDao.insert(new Jeu("Rayman Legends", 39.99, "Plateforme",
                    "Rejoignez Rayman et ses amis dans des niveaux colorés et remplis de défis pour sauver le monde des créatures maléfiques."));
            JeuDao.insert(new Jeu("Assassin's Creed Origins", 59.99, "Action-aventure",
                    "Découvrez l'histoire des origines de la confrérie des Assassins dans l'Égypte antique, et frayez-vous un chemin à travers des batailles épiques et des conspirations."));
            JeuDao.insert(new Jeu("Terraria", 19.99, "Sandbox",
                    "Explorez, construisez et combattez dans un monde rempli de créatures mystérieuses et de trésors cachés."));
            JeuDao.insert(new Jeu("Final Fantasy XIV", 29.99, "RPG",
                    "Plongez dans le monde de la saga légendaire Final Fantasy avec des milliers d'autres joueurs dans ce MMORPG riche en histoires et en combats épiques."));
            JeuDao.insert(new Jeu("Celeste", 19.99, "Plateforme",
                    "Aidez Madeline à surmonter ses peurs intérieures et à escalader la montagne de Celeste dans ce jeu de plateforme acclamé."));
            JeuDao.insert(new Jeu("Red Dead Redemption 2", 59.99, "Action-aventure",
                    "Explorez le Far West américain à l'époque des hors-la-loi et participez à des braquages, des duels et des aventures épiques."));
            JeuDao.insert(new Jeu("Stardew Valley", 14.99, "Sandbox",
                    "Quittez la ville pour hériter de la vieille ferme de votre grand-père et transformez-la en un lieu prospère en cultivant des cultures, en élevant du bétail et en tissant des liens avec les habitants de la ville."));
            JeuDao.insert(new Jeu("The Elder Scrolls V: Skyrim", 39.99, "RPG",
                    "Un vaste monde ouvert avec des quêtes épiques, des dragons redoutables et des mystères à découvrir."));
            JeuDao.insert(new Jeu("Batman: Arkham City", 39.99, "Action-aventure",
                    "Incarnez Batman et combattez les super-vilains dans les rues sombres et corrompues d'Arkham City."));
            JeuDao.insert(new Jeu("Minecraft: Dungeons", 29.99, "Action-aventure",
                    "Plongez dans des donjons générés de manière procédurale, affrontez des monstres redoutables et obtenez des trésors légendaires dans ce jeu d'action-aventure inspiré de l'univers de Minecraft."));
            JeuDao.insert(new Jeu("RimWorld", 34.99, "Sandbox",
                    "Gérez une colonie de survivants échoués sur une planète extraterrestre dans ce jeu de gestion de colonie complexe et captivant."));
            JeuDao.insert(new Jeu("Final Fantasy VII Remake", 59.99, "RPG",
                    "Revivez l'histoire épique de Cloud Strife et du groupe de résistants Avalanche dans ce remake du célèbre jeu de rôle japonais."));
            JeuDao.insert(new Jeu("Crash Bandicoot N. Sane Trilogy", 39.99, "Plateforme",
                    "Redécouvrez les aventures classiques de Crash Bandicoot dans une collection remasterisée comprenant les trois premiers jeux de la série."));
            JeuDao.insert(new Jeu("The Outer Worlds", 49.99, "RPG",
                    "Explorez un univers de science-fiction dystopique, prenez des décisions morales difficiles et façonnez votre propre destinée dans ce RPG captivant."));
            JeuDao.insert(new Jeu("Super Smash Bros. Ultimate", 59.99, "Action-aventure",
                    "Affrontez vos personnages préférés de Nintendo et d'autres franchises dans des combats épiques mettant en scène des crossovers incroyables."));
            JeuDao.insert(new Jeu("Sekiro: Shadows Die Twice", 59.99, "Action-aventure",
                    "Affrontez des ennemis redoutables en tant que 'Loup à un bras' dans ce jeu d'action-aventure intense et exigeant développé par FromSoftware."));
            JeuDao.insert(new Jeu("Subnautica", 29.99, "Sandbox",
                    "Plongez dans les profondeurs mystérieuses d'une planète océanique extraterrestre et survivez en explorant, en construisant et en découvrant les secrets de ce monde aquatique hostile."));
            JeuDao.insert(new Jeu("Divinity: Original Sin 2", 44.99, "RPG",
                    "Plongez dans un vaste monde de fantasy et menez une équipe de héros dans une quête pour sauver le monde de la destruction dans ce RPG isométrique riche en choix et en conséquences."));

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








