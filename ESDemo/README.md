## 关于依赖
部分依赖可以在这个仓库下载：

```
	<repositories>
		<!-- add the elasticsearch repo -->
		<repository>
			<id>elasticsearch-releases</id>
			<url>https://artifacts.elastic.co/maven</url>
			<releases>
				<enabled>true</enabled>
			</releases>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
	</repositories>
```