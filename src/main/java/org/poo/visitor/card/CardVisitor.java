package org.poo.visitor.card;

import org.poo.model.card.OneTimePayCard;
import org.poo.model.card.RegularCard;

public interface CardVisitor {
    void visit(RegularCard card);
    void visit(OneTimePayCard card);
}
