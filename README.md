# 🌐 Multithreaded Web Server  

A high-performance, multi-threaded web server designed to handle concurrent HTTP requests efficiently. Built with Java, this server demonstrates robust scalability, responsiveness, and stability under heavy load.

---

## 🚀 Features  

### 🔄 Multi-Threading  
- Concurrently handles HTTP requests using a multi-threaded architecture.  
- Enhances responsiveness and ensures smooth system performance.

### ⚡ Socket Communication  
- Uses Java sockets for seamless client-server communication.  
- Allows clients to connect, send HTTP requests, and process responses effortlessly.

### 🧵 Thread Pool Optimization  
- Incorporates a configurable thread pool for efficient resource utilization.  
- Prevents crashes during heavy loads and ensures stability.

---

## 📊 Performance Comparison  

### 🛠 Single-Threaded vs. Multi-Threaded  
- **Single-Threaded:** Significant delays in handling **10,000 requests per second**.  
- **Multi-Threaded:** Handles multiple requests concurrently with superior performance.  

### ✅ Thread Pool Advantage  
- Default thread pool size: **100**.  
- Handles **600,000 requests in 60 seconds** during stress testing without crashing.

---

## 🧪 Load Testing with JMeter  

### Why JMeter?  
✔️ **Scalability Testing:** Simulates real-world user scenarios at scale.  
✔️ **Performance Monitoring:** Provides insights into bottlenecks.  
✔️ **Stress Testing:** Pushes the application to its limits under extreme conditions.

### Steps to Use JMeter:  
1. **Install JMeter**: Download from the [official website](https://jmeter.apache.org/).  
2. **Run JMeter**: Open and load the test plan from the `jmeter-tests` directory.  
3. **Configure Test Plan**: Customize parameters like server URL, threads, and duration.  
4. **Start Test**: Run the test and monitor the real-time results.  
5. **Analyze Reports**: Evaluate metrics like response time, throughput, and error rates.

---

## 📂 Usage  

### Prerequisites  
- Install the **Java Development Kit (JDK)**.  
- Clone the repository:  
  ```bash
  git clone https://github.com/byanaknoob/MULTITHEARED-WEBSERVER.git
