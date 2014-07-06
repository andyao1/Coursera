function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples
n = length(theta);

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta

p1 = log(sigmoid(theta' * X')) .* y';
p2 = log(ones(1,m) - sigmoid(theta' * X')) .* (ones(1,m) - y');
p3 = (theta .* theta);

coef = ones(size(theta));
coef(1) = 0;
J = (ones(1, m) * (-p1 - p2)') / m + lambda * (coef' * (theta .* theta)) / (2 * m);
grad = (sigmoid(theta' * X') - y') * X / m + lambda * (coef' .* theta') / m; 





% =============================================================

end
