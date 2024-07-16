// 데이터를 가져와서 처리하는 함수
async function renderSneakers() {
    try {
        // 서버에서 신발 데이터를 가져옵니다.
        const sneakersData = await fetchSneakersData();
        
        // 스니커즈 목록을 생성하고 해당 div에 추가합니다.
        const sneakersListDiv = document.getElementById('sneakersList');
        sneakersData.forEach(sneakers => {
            // 각 스니커즈에 대한 카드를 생성합니다.
            const cardDiv = document.createElement('div');
            cardDiv.classList.add('sneakers-card');

            // 이미지를 추가합니다.
            const image = document.createElement('img');
            image.classList.add('sneakers-image');
            image.src = sneakers.image;
            image.alt = sneakers.title;
            cardDiv.appendChild(image);

            // 스니커즈 정보를 추가합니다.
            const detailsDiv = document.createElement('div');
            detailsDiv.classList.add('sneakers-details');

            const title = document.createElement('h2');
            title.classList.add('sneakers-title');
            title.textContent = sneakers.title;
            detailsDiv.appendChild(title);

            const description = document.createElement('p');
            description.classList.add('sneakers-description');
            description.textContent = sneakers.description;
            detailsDiv.appendChild(description);

            const price = document.createElement('p');
            price.classList.add('sneakers-price');
            price.textContent = sneakers.price;
            detailsDiv.appendChild(price);

            cardDiv.appendChild(detailsDiv);

            // 스니커즈 카드를 스니커즈 목록에 추가합니다.
            sneakersListDiv.appendChild(cardDiv);
        });
    } catch (error) {
        console.error('Error rendering sneakers:', error);
    }
}
