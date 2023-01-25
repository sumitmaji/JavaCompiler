package com.sum.intermediatei.ast;

public interface NodeVisitor {
    void visit(AddNode node);
    void visit(VariableNode node);

    void visit(AndNode andNode);

    void visit(AssignNode assignNode);

    void visit(BooleanConstNode booleanConstNode);

    void visit(CompoundNode compoundNode);

    void visit(EqualToNode equalToNode);

    void visit(FieldNode fieldNode);

    void visit(FloatDivNode floatDivNode);

    void visit(GreaterThanEqualToNode greaterThanEqualToNode);

    void visit(GreaterThanNode greaterThanNode);

    void visit(IntConstNode intConstNode);

    void visit(IntDivNode intDivNode);

    void visit(LessThanEqualToNode lessThanEqualToNode);

    void visit(LessThanNode lessThanNode);

    void visit(ModNode modNode);

    void visit(MulNode mulNode);

    void visit(NegateNode negateNode);

    void visit(NoOpNode noOpNode);

    void visit(NotEqualToNode notEqualToNode);

    void visit(NotNode notNode);

    void visit(OrNode orNode);

    void visit(ReadConstNode readConstNode);

    void visit(StringConstNode stringConstNode);

    void visit(SubscriptNode subscriptNode);

    void visit(SubstractNode substractNode);

    void visit(VariableDeclNode variableDeclNode);

    void visit(CallNode callNode);

    void visit(ParametersNode parametersNode);
}
