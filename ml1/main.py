import tensorflow._api.v2.compat.v1 as tf
tf.disable_v2_behavior()

xData = [1, 2, 3, 4, 5, 6, 7]
yData = [25000, 55000, 75000, 110000, 128000, 155000, 180000]


# weight basis
W = tf.Variable(tf.random_uniform([1], -100, 100))
b = tf.Variable(tf.random_uniform([1], -100, 100))


X = tf.placeholder(tf.float32)
Y = tf.placeholder(tf.float32)

H = W * X + b

# cost function
cost = tf.reduce_mean(tf.square(H - Y))

# how much you wanna jump at one
a = tf.Variable(0.01)
optimizer = tf.train.GradientDescentOptimizer(a)

# optimize by minimize cost
train = optimizer.minimize(cost)

init = tf.global_variables_initializer()

sess = tf.Session()
sess.run(init)


for i in range(5001):
    sess.run(train, feed_dict={X: xData, Y: yData})
    if i % 500 == 0:
        print(i, sess.run(cost, feed_dict={
              X: xData, Y: yData}), sess.run(W), sess.run(b))

print("weight : ", sess.run(W))
print("basis : ", sess.run(b))
print(sess.run(H, feed_dict={X: [8]}))
