/**
 * Ce logiciel est distribué à des fins éducatives.
 * <p>
 * Il est fourni "tel quel", sans garantie d’aucune sorte, explicite
 * ou implicite, notamment sans garantie de qualité marchande, d’adéquation
 * à un usage particulier et d’absence de contrefaçon.
 * En aucun cas, les auteurs ou titulaires du droit d’auteur ne seront
 * responsables de tout dommage, réclamation ou autre responsabilité, que ce
 * soit dans le cadre d’un contrat, d’un délit ou autre, en provenance de,
 * consécutif à ou en relation avec le logiciel ou son utilisation, ou avec
 * d’autres éléments du logiciel.
 * <p>
 * (c) 2022-2024 Romain Wallon - Université d'Artois.
 * Tous droits réservés.
 */

package fr.univartois.butinfo.r304.bomberman.view;

import javafx.scene.image.Image;

/**
 * Le record {@link Sprite} représente un élément graphique du jeu.
 * Il s'agit d'un objet encapsulant une image sans état interne, et qui peut être placé à
 * un endroit donné de la fenêtre.
 * De cette manière, il est possible d'utiliser la même instance de {@link Sprite} pour
 * représenter plusieurs éléments similaires en même temps.
 *
 * @param image L'image associée à cette instance de {@link Sprite}.
 * @author Romain Wallon
 * @version 0.1.0
 */
public record Sprite(Image image) {


    /**
     * Donne la largeur de l'image associée, mesurée en nombre de pixels.
     *
     * @return La largeur de l'image associée.
     */
    public int getWidth() {
        return (int) image.getWidth();
    }

    /**
     * Donne la hauteur de l'image associée, mesurée en nombre de pixels.
     *
     * @return La hauteur de l'image associée.
     */
    public int getHeight() {
        return (int) image.getHeight();
    }

    /**
     * Donne l'image associée à cette instance de {@link Sprite}.
     *
     * @return L'image associée à cette instance de {@link Sprite}.
     */
    public Image getImage() {
        return image;
    }
}
